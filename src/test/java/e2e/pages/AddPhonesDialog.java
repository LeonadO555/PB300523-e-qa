package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPhonesDialog extends PhonesPage {
    public AddPhonesDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropdown;
    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneInputField;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement closeWindowsButton;

    @Step("")
    public void waitForOpen() {
        getWait().forVisibility(dialog);
        getWait().forVisibility(countryCodeDropdown);
        getWait().forVisibility(phoneInputField);
        getWait().forVisibility(saveButton);
        getWait().forVisibility(closeWindowsButton);
    }
    @Step("Select country code: {country}")
    public void selectCodeCountry(String code){
        getSelect(countryCodeDropdown).selectByVisibleText(code);
    }
    @Step
    public void setPhoneInput(String phone){
        phoneInputField.sendKeys(phone);
    }
    @Step
    public void savePhoneNumber(){
        saveButton.click();
    }

    public void waitForLoading() {
    }
}




