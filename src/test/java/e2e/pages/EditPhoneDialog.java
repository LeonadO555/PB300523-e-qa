package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPhoneDialog extends PhoneInfoPage {
    public EditPhoneDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='cc-select']")
    WebElement countryCodeDropDown;
    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneInputField;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    @Step("wait")
    public void waitForOpen() {
        getWait().forVisibility(countryCodeDropDown);
        getWait().forVisibility(phoneInputField);
        getWait().forVisibility(saveButton);
        getWait().forClickable(saveButton);

    }
    @Step
    public void selectCountryCode(String code){
        getSelect(countryCodeDropDown).selectByVisibleText(code);
    }
    @Step
    public void setEditPhone(String number) {
        setInput(phoneInputField, number);
    }
    @Step
    public void savePhoneChanges() {
        saveButton.click();
        getWait().forInvisibility(saveButton);
    }
}
