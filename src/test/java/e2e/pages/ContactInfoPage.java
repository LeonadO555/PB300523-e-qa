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
    WebElement contactDescriptionField;
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
}
