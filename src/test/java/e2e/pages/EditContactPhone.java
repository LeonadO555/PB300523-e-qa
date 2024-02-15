package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditContactPhone extends  ContactInfoPage{
    public EditContactPhone(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='ngb-nav-3']")
    WebElement contactPhoneNumber;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneNumber;
    @FindBy(xpath = "//*[@id='search']")
    WebElement search;
    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement openCountryCode;
    @FindBy(xpath = "//*[@id='cc-select']/option[80]")
    WebElement ccDE;
    
}
