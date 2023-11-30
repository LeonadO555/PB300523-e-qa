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

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    // Describe locator
    @FindBy(xpath = "//*[@id='form-name']")
    WebElement firstNameInput;

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

    public void waitForOpen() {
        getWait().forVisibility(dialog);
        getWait().forVisibility(firstNameInput);
        getWait().forVisibility(lastNameInput);
        getWait().forVisibility(descriptionInput);
        getWait().forVisibility(saveButton);

    }

    public void setFirstNameInput(String firstName) {
        setInput(firstNameInput, firstName);

        //firstNameInput.click();
        //firstNameInput.clear();
        //firstNameInput.sendKeys(firstName);
    }

    public void setLastNameInput(String lastName) {
        setInput(lastNameInput, lastName);
        //lastNameInput.click();
        //lastNameInput.clear();
        //lastNameInput.sendKeys(lastName);
    }

    public void setDescription(String description) {
        setInput(descriptionInput, description);
        //descriptionInput.click();
        //descriptionInput.clear();
        //descriptionInput.sendKeys(description);
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
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }


    }
}