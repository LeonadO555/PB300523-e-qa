package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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


    public void waitForLoading() {
        getWait().forVisibility(searchInput);
        getWait().forVisibility(addPhoneButton);

    }
    public void openPhoneButton() throws InterruptedException {
        addPhoneButton.click();
        getWait().forVisibility(addPhoneButton);}

    public String getCountry() {
        return countryCodeField.getText();
    }
    public  String getPhoneNumber(){
        return phoneNumberField.getText();
    }
//    public void selectEditButton(String edit){
//        getSelect(editDeleteDropdown).selectByVisibleText(edit);
//    }
//    public String getEditButton() {
//        return getSelect(editDeleteDropdown).getFirstSelectedOption().getText();
//    }

    public void openEditDeleteDropdown() {
        dropdown.click();
        getWait().forVisibility(dropdown);}

    public void clickEditButton()throws InterruptedException{
        editButton.click();
        getWait().forVisibility(editButton);
    }
    public EditPhoneForm openEditPhoneForm(){
        dropdown.click();
        getWait().forVisibility(editButton);
        editButton.click();
        return new EditPhoneForm(driver);
    }
    public void deletePhone(){
        dropdown.click();
        getWait().forVisibility(removePhoneButton);
        removePhoneButton.click();
    }

    }





