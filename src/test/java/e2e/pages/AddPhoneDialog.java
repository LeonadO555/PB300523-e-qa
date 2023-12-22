package e2e.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPhoneDialog extends PhoneInfoPage {
    public AddPhoneDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeDropdown;
    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneInputField;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    public void waitForOpen() {
        getWait().forVisibility(dialog);
        getWait().forVisibility(countryCodeDropdown);
        getWait().forVisibility(phoneInputField);
        getWait().forVisibility(saveButton);

    }
    public void selectCodeCountry(String code){
        getSelect(countryCodeDropdown).selectByVisibleText(code);
    }
    public void setPhoneInput(String phone){
        phoneInputField.sendKeys(phone);
    }
    public void savePhoneNumber(){
        try {
            getWait().forClickable(saveButton);
            saveButton.click();
            getWait().forInvisibility(dialog);
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
    }
}
}




