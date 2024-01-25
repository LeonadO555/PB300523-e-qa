package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PhonesPage extends ContactInfoPage {
    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@'id = items-table-phone'")
    WebElement table;

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

    @Step
    public void waitForLoading() {
        getWait().forVisibility(searchInput);
        getWait().forVisibility(addPhoneButton);

    }
    @Step
    public void openPhoneButton() {
        addPhoneButton.click();
        getWait().forVisibility(addPhoneButton);}
    @Step
    public String getCountry() {
        return countryCodeField.getText();
    }
    @Step
    public  String getPhoneNumber(){
        return phoneNumberField.getText();
    }
//    public void selectEditButton(String edit){
//        getSelect(editDeleteDropdown).selectByVisibleText(edit);
//    }
//    public String getEditButton() {
//        return getSelect(editDeleteDropdown).getFirstSelectedOption().getText();
//    }
@Step
    public void openEditDeleteDropdown() {
        dropdown.click();
        getWait().forVisibility(dropdown);}
    @Step
    public void clickEditButton(){
        editButton.click();
        getWait().forVisibility(editButton);
    }
    @Step
    public EditPhoneForm openEditPhoneForm(){
        dropdown.click();
        getWait().forVisibility(editButton);
        editButton.click();
        return new EditPhoneForm(driver);
    }
    @Step
    public void deletePhone(){
        dropdown.click();
        getWait().forVisibility(removePhoneButton);
        removePhoneButton.click();
    }
    @Step
    public  void takePhonePageScreenshot(){
        takeAndCompareScreenshot("Phone page", null);
    }

    }





