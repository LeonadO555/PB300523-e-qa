package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhoneInfoPage extends ContactInfoPage{
    public PhoneInfoPage(WebDriver driver) {
        super(driver);
    }

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
    }
    public void clickOnAddPhoneButton(){
        addPhoneButton.click();
    }
    public EditPhoneDialog openEditPhoneDialog() {
        optionDropDown.click();
        getWait().forVisibility(editPhoneButton);
        editPhoneButton.click();
        return new EditPhoneDialog(driver);
    }
    public String getCountryCode(){
        return countryCodeField.getText();
    }

    public String getPhoneNumber(){
        return phoneInputField.getText();
    }
    public void filterByPhone(String phoneCheckValue){
        searchInput.sendKeys(phoneCheckValue);
    }
    public void deletePhone() {
        optionDropDown.click();
        getWait().forVisibility(removePhoneButton);
        removePhoneButton.click();
    }
}

