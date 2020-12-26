package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private static Manufacturer manufacturer;
    private static boolean dataInitialized = true;


    @BeforeEach
    public void setup(WebApplicationContext wac){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    public void initData(){
        if(dataInitialized){
            manufacturer = manufacturerService.save("m1",new Country("USA"),"LOS ANGELES").get();
            manufacturerService.save("m2",new Country("Germany"),"BERLIN");

            userService.register("user","user","user","user","user", Role.ROLE_USER);
            userService.register("admin","admin","admin","admin","admin",Role.ROLE_ADMIN);

            dataInitialized = false;
        }
    }


    @Test
    void contextLoads() {
    }


    @Test
    public void testGetBalloon() throws Exception {
        MockHttpServletRequestBuilder balloonRequest = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent","listBalloons"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }

}
