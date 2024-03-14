package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPhoneNumberDialog extends EditContactPhone {
    public AddPhoneNumberDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@for='cc-select']")
    WebElement countryCodeLabel;

    @FindBy(xpath = "//*[@for='selected-cc']")
    WebElement phoneNumberLabel;

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropdown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@role='alert']")
    WebElement alert;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement submitButton;

    @FindBy(xpath = "//*[@type='button']//*[@aria-hidden='true']")
    WebElement closeEditPhoneForm;

    public void waitForOpen(){
        getWait().forVisibility(countryCodeDropdown);
        getWait().forVisibility(phoneNumberInput);
        getWait().forVisibility(submitButton);
        getWait().forVisibility(phoneNumberLabel);
        getWait().forVisibility(countryCodeLabel);
    }


    public void selectCountryCode(String countryCode) {
        getSelect(countryCodeDropdown).selectByVisibleText(countryCode);
    }

    public String getCountryCode() {
        return getSelect(countryCodeDropdown).getFirstSelectedOption().getText();
    }

    public String getPhoneNumber() {
        return phoneNumberInput.getText();
    }

    public void setPhoneNumberInput(String phoneNumber) {
        setInput(phoneNumberInput, phoneNumber);
    }

    public void savePhone() throws InterruptedException {
        submitButton.click();
        getWait().forInvisibility(submitButton);
    }
}


