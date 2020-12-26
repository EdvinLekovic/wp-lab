package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {

    private WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static void get(WebDriver webDriver,String relativeUrl){
        String url = System.getProperty("getb.build.baseUrl","http://localhost:9999")+relativeUrl;
        webDriver.get(url);
    }

}
