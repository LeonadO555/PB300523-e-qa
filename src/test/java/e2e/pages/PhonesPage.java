package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhonesPage extends ContactInfoPage{
    public PhonesPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneNumberButton;
    @FindBy(xpath = "//*[@class='row-table-cc']")
    WebElement countryCodeField;
    @FindBy(xpath = "//*[@class='row-table-pn']")
    WebElement phoneNumberField;

    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forVisibility(addPhoneNumberButton);
    }

    public String getCountryCode(){
        return countryCodeField.getText();
    }
    public String getPhoneNumber(){
        return phoneNumberField.getText();
    }
}
