package e2e.pages.profile;

import e2e.enums.ContactInfoTabs;
import e2e.pages.contact.ContactsPage;
import e2e.pages.contact.EditContactForm;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProfilePage extends ContactsPage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='list-group-item list-group-item-action']")
    WebElement editProfile;

    @FindBy(xpath = "//*[@id='btn-edit-contact']")
    WebElement editButton;

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@name='input-ec-firstName']")
    WebElement firstNameInput;

    @FindBy(xpath = "//*[@name='input-ec-lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//*[@name='input-ec-description']")
    WebElement descriptionInput;

    @FindBy(xpath = "//*[@class='btn btn-secondary cancel-btn-ec']")
    WebElement cancelButton;

    @FindBy(xpath = "//*[@class='btn btn-primary submit-btn-ec']")
    WebElement saveButton;

    @Step
    public void waitForLoading(){
        getWait().forVisibility(editProfile);
        getWait().forClickable(editButton);
        getWait().forVisibility(firstNameInput);
        getWait().forVisibility(lastNameInput);
        getWait().forVisibility(descriptionInput);
        getWait().forVisibility(saveButton);

    }

    @Step("Open tab: {tab}")
    public void openTab(ContactInfoTabs tab){
        driver.findElement(By.xpath("//*[@ng-reflect-_id='"+tab.value+"']")).click();
    }

    @Step
    public String getFirstName() {
        return firstNameInput.getText();
    }
    @Step
    public String getLastName() {
        return lastNameInput.getText();
    }
    @Step
    public String getDescription() {
        return descriptionInput.getText();
    }
    @Step
    public EditContactForm openEditContactForm() {
        editButton.click();
        Assert.assertFalse(isElementDisplayed(firstNameInput), "Edit contact form was not opened");
        return new EditContactForm(driver);
    }
    @Step
    public void takeUserPageScreenshot(){
        takeAndCompareScreenshot("userPage", null);
    }
}
