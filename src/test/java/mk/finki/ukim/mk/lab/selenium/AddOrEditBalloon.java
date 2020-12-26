package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage {


    private WebElement name;
    private WebElement desc;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver webDriver) {
        super(webDriver);
    }

    public static BalloonPage addBalloon(WebDriver driver,String name,String desc,String manufacturer){
        get(driver,"/balloons/add-form");
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver,AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.desc.sendKeys(desc);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '"+manufacturer +"']")).click();
        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver,BalloonPage.class);
    }

    public static BalloonPage editBalloon(WebDriver driver,WebElement editButton,String name,String desc,String manufacturer){
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver,AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.desc.sendKeys(desc);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '"+manufacturer +"']")).click();
        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver,BalloonPage.class);
    }


}
