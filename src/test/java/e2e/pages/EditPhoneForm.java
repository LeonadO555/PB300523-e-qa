package e2e.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPhoneForm extends PhonesPage {
    public EditPhoneForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropDown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    public void waitForOpen() {
        getWait().forVisibility(countryCodeDropDown);
        getWait().forVisibility(phoneNumberInput);
        getWait().forVisibility(saveButton);
    }

    public void selectCountryCode(String country) {
        getSelect(countryCodeDropDown).selectByVisibleText(country);
    }

    public String getCountry() {
        return getSelect(countryCodeDropDown).getFirstSelectedOption().getText();
    }

    public void setPhoneNumberInput(String phoneNumber) {
        setInput(phoneNumberInput, phoneNumber);
    }

    public void saveChange() {
        saveButton.click();
        getWait().forInvisibility(saveButton);
    }
    }

