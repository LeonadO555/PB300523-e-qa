package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditContactPhone extends  ContactInfoPage{
    public EditContactPhone(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropDown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@for='cc-select']")
    WebElement phoneCountryLabel;

    @FindBy(xpath = "//*[@for='selected-cc']")
    WebElement phoneNumberLabel;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    public void waitForOpen(){
        getWait().forVisibility(searchInput);
        getWait().forVisibility(countryCodeDropDown);
        getWait().forVisibility(phoneNumberInput);
        getWait().forVisibility(phoneNumberLabel);
        getWait().forVisibility(phoneCountryLabel);
        getWait().forVisibility(saveButton);
        getWait().forClickable(saveButton);
    }
    public void selectCountryCode(String country){
        getSelect(countryCodeDropDown).selectByVisibleText(country);
    }
    public String getCountry(){
        return getSelect(countryCodeDropDown).getFirstSelectedOption().getText();
    }
    public void setPhoneNumberInput(String phoneNumber){
        setInput(phoneNumberInput, phoneNumber);
    }

    public void savePhone () throws InterruptedException{
        saveButton.click();
        getWait().forInvisibility(saveButton);
    }
}
