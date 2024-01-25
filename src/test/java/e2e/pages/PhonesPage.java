package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhonesPage extends ContactInfoPage{
    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[id='items-table-phone']")
    WebElement table;

    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")

    WebElement addPhoneButton;
    @FindBy(xpath = "//*[@class='custom-select']")
    WebElement countryCodeField;
    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneInputField;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;
    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-edit']")
    WebElement editPhoneButton;
    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-remove']")
    WebElement removePhoneButton;
    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addPhoneButton);
        getWait().forVisibility(table);
    }
    @Step
    public void clickOnAddPhoneButton(){
        addPhoneButton.click();
    }
    @Step
    public EditPhoneDialog openEditPhoneDialog() {
        optionDropDown.click();
        getWait().forVisibility(editPhoneButton);
        editPhoneButton.click();
        return new EditPhoneDialog(driver);
    }
    @Step
    public String getCountryCode(){
        return countryCodeField.getText();
    }

    @Step
    public String getPhoneNumber(){
        return phoneInputField.getText();
    }
    @Step
    public void filterByPhone(String phoneCheckValue){
        searchInput.sendKeys(phoneCheckValue);
    }
    @Step
    public void deletePhone() {
        optionDropDown.click();
        getWait().forVisibility(removePhoneButton);
        removePhoneButton.click();
    }

    @Step
    public void takePhonesPageScreenshot() {
        takeAndCompareScreenshot("phonesPage", null);
    }
}


