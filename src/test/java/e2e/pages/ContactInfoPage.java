package e2e.pages;

import e2e.enums.ContactInfoTabs;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactInfoPage extends ContactsPage {
    public ContactInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='contact-first-name']")
    WebElement firstNameField;
    @FindBy(xpath = "//*[@id='contact-last-name']")
    WebElement lastNameField;
    @FindBy(xpath = "//*[@id='contact-description']")
    WebElement descriptionField;
    @FindBy(xpath = "//*[@id='btn-edit-contact']")
    WebElement editButton;
    @Step
    public void waitForLoading(){
        getWait().forVisibility(firstNameField);
        getWait().forVisibility(lastNameField);
        getWait().forVisibility(descriptionField);

    }
    @Step("Open tab:{tab}")
    public void openTab(ContactInfoTabs tab){
        driver.findElement(By.xpath("//*[@ng-reflect-_id='"+tab.value+"']")).click();
    }
    @Step
    public String getFirstName() {
        return firstNameField.getText();
    }
    @Step
    public String getLastName() {
        return lastNameField.getText();
    }
    @Step
    public String getDescription() {
        return descriptionField.getText();
    }
    @Step
    public EditContactForm openEditContactForm() {
        editButton.click();
        Assert.assertFalse(isElementDisplayed(firstNameField), "Edit contact form was not opened");
        return new EditContactForm(driver);
    }
}

