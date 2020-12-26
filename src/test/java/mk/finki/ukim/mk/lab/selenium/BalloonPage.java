package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage{

    @FindBy(css="tr[class=balloon]")
    private List<WebElement> balloonRows;

    @FindBy(css=".delete-balloon")
    private List<WebElement> deleteButtons;

    @FindBy(css=".edit-balloon")
    private List<WebElement> editButtons;

    @FindBy(css=".add-balloon")
    private List<WebElement> addBalloonButtons;

    public BalloonPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static BalloonPage to(WebDriver driver){
        get(driver,"/balloons");
        return PageFactory.initElements(driver,BalloonPage.class);
    }

    public void assertElements(int balloonNumber,int deleteButtons,int editButtons,int addButtons){

        Assert.assertEquals("Balloon number do not match",balloonNumber,getBalloonRows().size());
        Assert.assertEquals("DeleteButtons do not match",deleteButtons,getDeleteButtons().size());
        Assert.assertEquals("EditButtons do not match",editButtons,getEditButtons().size());
        Assert.assertEquals("AddButtons do not match",addButtons,getAddBalloonButtons().size());
    }
}
