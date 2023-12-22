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
    WebElement countryCodeDropdown;

    @FindBy(xpath = "//*[@id='selected-cc']")
    WebElement phoneInputField;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;


    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addPhoneButton);
    }

    public void clickOnAddPhoneButton(){
        addPhoneButton.click();
    }
    public String getCountryCode(){
        return countryCodeDropdown.getText();
    }

    public String getPhoneNumber(){
        return phoneInputField.getText();
    }

    public void clickOnDropdownButtonEdit(String edit){
        getSelect(optionDropDown).selectByVisibleText(edit);
    }
    public void clickOnDropdownButtonDelete(String remove){
        getSelect(optionDropDown).selectByVisibleText(remove);
    }
}

