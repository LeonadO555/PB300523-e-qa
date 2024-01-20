package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailInfoPage extends ContactInfoPage {
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
    @FindBy(xpath = "//*[@class='dropdown-item btn-email-edit']")
    WebElement editEmailButton;
    @FindBy(xpath = "//*[@class='dropdown-item btn-email-remove']")
    WebElement removeEmailButton;
    public void waitForLoading() {
        getWait().forVisibility(searchInput);
        getWait().forClickable(addEmailButton);
    }
    public void clickOnAddEmailButton() {
        addEmailButton.click();
    }
    public EditEmailDialog openEditEmailDialog() {
        optionDropDown.click();
        getWait().forVisibility(editEmailButton);
        editEmailButton.click();
        return new EditEmailDialog(driver);
    }
    public String getEmail() {
        return emailInputField.getText();
    }
    public void tabDropDawn() {
        optionDropDown.click();
    }
    public void filterByEmail(String emailCheck){
        searchInput.sendKeys(emailCheck);
    }
    public void deleteEmail() {
        optionDropDown.click();
        getWait().forVisibility(removeEmailButton);
        removeEmailButton.click();
    }
}