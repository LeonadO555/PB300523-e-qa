package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EditContactForm extends ContactInfoPage{

        public EditContactForm(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//class[@name='btn btn-secondary cansel-btn-ec']")
        WebElement canselButton;
        @FindBy(xpath = "//*[@class='btn btn-primary submit-btn-ec']")
        WebElement saveButton;
        @FindBy(xpath = "//input[@name='input-ec-firstName']")
        WebElement firstNameInput;
        @FindBy(xpath = "//input[@name='input-ec-lastName']")
        WebElement lastNameInput;
        @FindBy(xpath = "//*[@name='input-ec-description']")
        WebElement descriptionInput;

    public void waitForOpen(){
        getWait().forInvisibility(firstNameInput);
        getWait().forInvisibility(lastNameInput);
        getWait().forInvisibility(descriptionInput);
        getWait().forInvisibility(firstNameField);
        getWait().forInvisibility(saveButton);
        getWait().forClickable(saveButton);
    }

        public void setFirstNameInput(String firstName){
            setInput(firstNameInput, firstName);
    }
    public void setLastNameInput(String lastName) {
            setInput(lastNameInput, lastName);

    }

    public void  setDescriptionInput( String description){
            setInput(descriptionInput, description);
    }

    public void saveChanges() throws InterruptedException {
            saveButton.click();
            Thread.sleep(200);
            Assert.assertFalse(isElementDisplayed(descriptionInput), "Edit contact form was opened");
    }
    }
