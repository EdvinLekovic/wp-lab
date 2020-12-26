package mk.finki.ukim.mk.lab.selenium;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = true;
    private String user = "user";
    private String admin = "admin";
    @BeforeEach
    public void setup(){
        driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if(driver!= null){
            driver.close();
        }
    }

    public void initData(){
        if(dataInitialized){
         m1 = manufacturerService.save("m1",new Country("USA"),"LOS ANGELES").get();
         m2 = manufacturerService.save("m2",new Country("Switzerland"),"Zurich").get();


         regularUser = userService.register(user,user,user,user,user, Role.ROLE_USER);
         adminUser = userService.register(admin,admin,admin,admin,admin,Role.ROLE_ADMIN);
         dataInitialized = false;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonPage balloonPage = BalloonPage.to(this.driver);
        //no user
        balloonPage.assertElements(0,0,0,0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        //admin
        balloonPage = LoginPage.doLogin(this.driver,loginPage,adminUser.getUsername(),admin);
        balloonPage.assertElements(0,0,0,1);

        balloonPage = AddOrEditBalloon.addBalloon(this.driver,"test1","test1 desc",m1.getName());
        balloonPage.assertElements(1,1,1,1);

        balloonPage = AddOrEditBalloon.addBalloon(this.driver,"test2","test2 desc",m2.getName());
        balloonPage.assertElements(2,2,2,1);

        balloonPage.getDeleteButtons().get(1).click();
        balloonPage.assertElements(1,1,1,1);

        balloonPage = AddOrEditBalloon.editBalloon(this.driver,balloonPage.getEditButtons().get(0),"test2","test2 desc",m2.getName());
        balloonPage.assertElements(1,1,1,1);

        //user
        loginPage = LoginPage.logout(this.driver);
        balloonPage = LoginPage.doLogin(this.driver,loginPage,regularUser.getUsername(),user);
        balloonPage.assertElements(1,0,0,0);

        //no user
        loginPage = LoginPage.logout(this.driver);
        balloonPage = BalloonPage.to(this.driver);
        balloonPage.assertElements(1,0,0,0);

    }





}
