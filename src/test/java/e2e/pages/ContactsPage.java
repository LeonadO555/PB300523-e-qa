package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactsPage extends BasePage{
    public ContactsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='collapse navbar-collapse']")
    WebElement header;

    public boolean confirmLogin(){
        return header.isDisplayed();
    }
}
