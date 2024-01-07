package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPhone extends PhoneInfoPage{
    public EditPhone(WebDriver driver) {
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
    public void setPhoneNumberInput(String editPhone){
        setInput(phoneNumberInput ,editPhone);

    }
    public void saveChange(){
        saveButton.click();
        getWait().forInvisibility(saveButton);
    }
    public void takeScreenshotEditedNumber(){
        takeAndCompareScreenshot("editPage",phoneNumberInput);
    }



}
