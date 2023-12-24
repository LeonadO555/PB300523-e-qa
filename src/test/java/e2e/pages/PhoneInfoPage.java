package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhoneInfoPage extends ContactInfoPage {
    public PhoneInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneButton;
    @FindBy(xpath = "//*[@class='row-table-pn']")
    WebElement phoneNumbers;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;

    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addPhoneButton);
    }

    public void clickOnAddPhoneButton(){
        addPhoneButton.click();
    }
    public String getPhone(){
         return phoneNumbers.getText();
    }
    public void clickOnDropdownButtonEdit(String Edit){
        getSelect(optionDropDown).selectByVisibleText(Edit);
    }
    public void clickOnDropdownButtonDelete(String Remove){
        getSelect(optionDropDown).selectByVisibleText(Remove);
    }


}
