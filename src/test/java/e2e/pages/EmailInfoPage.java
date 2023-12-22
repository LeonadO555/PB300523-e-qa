package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailInfoPage extends ContactInfoPage{
    //import constructor!!!
    public EmailInfoPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;
    @FindBy(xpath = "//*[@class='row-table w-95']")
    WebElement emailInputField;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-remove']")
    WebElement removeEmailButton;


    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addEmailButton);
    }

    public void clickOnAddEmailButton(){
        addEmailButton.click();
    }
    public String getEmail(){
        return emailInputField.getText();
    }

    public void tabDropDawn(){optionDropDown.click();

    }


    public DeleteEmail openEmailDelete(){
        getWait().forClickable(removeEmailButton);
        removeEmailButton.click();
        return new DeleteEmail(driver);
    }


    public void clickOnDropdownButtonEdit(String edit){
        getSelect(optionDropDown).selectByVisibleText(edit);
    }
    public void clickOnDropdownButtonDelete(String remove){
        getSelect(optionDropDown).selectByVisibleText(remove);
    }
}