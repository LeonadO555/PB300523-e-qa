package e2e.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddContactDialog extends ContactsPage {

    //import constructor!!!
    public AddContactDialog(WebDriver driver) {
        super(driver);
    }

    // Describe locator
    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;
    @FindBy(xpath = "//*[@id='form-name']")
    WebElement firstnameInput;

    @FindBy(xpath = "//*[@id='form-lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//*[@id='form-about']")
    WebElement descriptionInput;

    @FindBy(xpath = "//*[@type='reset']")
    WebElement cancelButton;

    @FindBy(xpath = "//*[@role='dialog']//*[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement closeWindowsButton;

    @FindBy(xpath = "//*[@id='form-error-firstName']")
    WebElement errorMessage;
    public void waitForOpen(){
        getWait().forInvisibility(dialog);
        getWait().forInvisibility(firstnameInput);
        getWait().forInvisibility(lastNameInput);
        getWait().forInvisibility(descriptionInput);
        getWait().forInvisibility(saveButton);

    }

    public void setFirstNameInput(String firstName) {
        setInput(firstnameInput, firstName);//смотреть в классе BasePage,можно сделать так,а можно написать логику для каждого!

    }

    public void setLastNameInput(String lastName) {
        setInput(lastNameInput, lastName);

    }

    public void setDescription(String description) {
        setInput(descriptionInput, description);
    }

    public void setAddContactForm(String firstName, String lastName, String description) {
        setFirstNameInput(firstName);
        setLastNameInput(lastName);
        setDescription(description);
    }

    public void saveContact() {
        try {
            getWait().forClickable(saveButton);
            saveButton.click();
            getWait().forInvisibility(dialog);
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }

    }
}
