package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPhoneDialog extends PhoneInfoPage{
    public AddPhoneDialog(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropdown;
    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    public void waitForOpen(){
        getWait().forVisibility(countryCodeDropdown);
        getWait().forVisibility(phoneNumberInput);
        getWait().forVisibility(saveButton);
    }
    public void waitForClose(){
        getWait().forInvisibility(countryCodeDropdown);
        getWait().forInvisibility(phoneNumberInput);
        getWait().forInvisibility(saveButton);
    }

    public void selectCountryCode(String country){
        getSelect(countryCodeDropdown).selectByVisibleText(country);
    }
    public void setPhoneNumber(String phoneNumber){
        phoneNumberInput.sendKeys(phoneNumber);
    }
    public void clickOnSaveButton(){
        saveButton.click();
    }
    public void takeScreenshotAddPhoneNumber(){
        takeAndCompareScreenshot("phoneNumber",phoneNumberInput);
    }

}
