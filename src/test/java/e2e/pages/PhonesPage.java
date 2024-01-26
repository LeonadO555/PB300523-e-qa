package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhonesPage extends ContactInfoPage {
    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@formcontrolname='searchInput']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneButton;

    @FindBy(xpath = "//*[@class='row-table-cc']")
    WebElement countryCodeField;

    @FindBy(xpath = "//*[@class='row-table-pn']")
    WebElement phoneNumberField;

    @FindBy(xpath = "//*[@class='nav-item ml-auto dropdown']")
    WebElement dropdown;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-edit']")
    WebElement editButton;

    @FindBy(xpath = "//*[@class='dropdown-item btn-phone-remove']")
    WebElement removePhoneButton;
    @FindBy(xpath = "//*[text()='Contacts']")
    WebElement contactsButton;

    @Step
    public void waitForLoading() {
        getWait().forVisibility(countryCodeField);
        getWait().forVisibility(phoneNumberField);

    }
    @Step
    public void openPhoneButton() {
        addPhoneButton.click();
        getWait().forVisibility(addPhoneButton);
    }
    @Step
    public String getCountry() {
        return countryCodeField.getText();
    }
    @Step
    public String getPhoneNumber() {
        return phoneNumberField.getText();
    }
    @Step
    public EditPhoneDialog openEditPhoneForm() {
        dropdown.click();
        getWait().forVisibility(editButton);
        editButton.click();
        return new EditPhoneDialog(driver);
    }
    @Step
    public void deletePhone() {
        dropdown.click();
        getWait().forVisibility(removePhoneButton);
        removePhoneButton.click();
    }
    @Step
    public ContactInfoPage openContactInfoPage(){
        contactsButton.click();
        return new ContactInfoPage(driver);
    }
    @Step
    public void takePhonesPagesScreenshot(){
        takeAndCompareScreenshot("phonesPage",null);
    }

}





