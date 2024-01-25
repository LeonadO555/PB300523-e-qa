package e2e.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactDialog extends ContactsPage {
    public AddContactDialog(WebDriver driver) {
        super(driver);
    }


        @FindBy(xpath = "//*[@role='dialog']")
        WebElement dialog;

        @FindBy(xpath = "//*[@href='/contacts']")
        WebElement addContactButton;

        @FindBy(xpath = "//*[@id='form-name']")
        WebElement firstNameInput;

        @FindBy(xpath = "//*[@id='form-lastName']")
        WebElement lastNameInput;

        @FindBy(xpath = "//*[@id='form-about']")
        WebElement descriptionInput;

        @FindBy(xpath = "//*[@type='reset']")
        WebElement resetAddContact;

        @FindBy(xpath = "//*[@role='dialog']//*[@type='submit']")
        WebElement submitAddContact;

        @FindBy(xpath = "//*[@aria-label='Close']")
        WebElement closeAddContact;

        @FindBy(xpath = "//*[@type='danger']")
        WebElement dangerAddContact;

        public void waitForOpen(){
            getWait().forVisibility(dialog);
            getWait().forVisibility(firstNameInput);
            getWait().forVisibility(lastNameInput);
            getWait().forInvisibility(descriptionInput);
            getWait().forVisibility(submitAddContact);
        }

        public void setFirstNameInput(String firstName){
            setInput(firstNameInput, firstName);
        }


        public void setLastNameInput(String lastName){
            setInput(lastNameInput, lastName);
        }

        public void setDescriptionContact(String description){
            setInput(descriptionInput, description);
        }

        public void setAddContactForm(String firstName, String lastName, String description){
            setFirstNameInput(firstName);
            setLastNameInput(lastName);
            setDescriptionContact(description);
    }

        public void saveContact(){
            try {
                getWait().forClickable(submitAddContact);
                submitAddContact.click();
                getWait().forInvisibility(dialog);
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
        }

    }

