package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage extends ContactsPage {
    public ContactInfoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='contact-first-name']")
    WebElement firstNameField;
    @FindBy(xpath = "//div[@id='contact-last-name']")
    WebElement lastNameField;
    @FindBy(xpath = "//div[@id='contact-description']")
    WebElement descriptionField;
    @FindBy(xpath = "//button[@id='btn-edit-contact']")
    WebElement editButton;
    @FindBy(xpath = "//button[@class='btn btn-secondary cancel-btn-ec']")
    WebElement cancelButton;
    @FindBy(xpath = "//button[@class='btn btn-primary submit-btn-ec']")
    WebElement saveButton;

    @FindBy(xpath = "//input[@name='input-ec-firstName']")
    WebElement firstNameInput;
    @FindBy(xpath = "//input[@name='input-ec-lastName]")
    WebElement lastNameInput;
    @FindBy(xpath = "//textarea[@name='input-ec-description']")
    WebElement descriptionInput;
    public String getFirstName(){
        return firstNameField.getText();
    }
    public String getLastName(){
        return lastNameField.getText();
    }
    public String getDescription(){
        return descriptionField.getText();
    }
    public void openEditContactForm(){
        editButton.click();
        isElementDisplayed(firstNameInput);
    }
    public void setFirstNameInput(String firstName){
        setInput(firstNameInput,firstName);
    }
}
