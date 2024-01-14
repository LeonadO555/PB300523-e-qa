package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactsPage extends BasePage{
    public ContactsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']")
    public WebElement header;
    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//*[@href='/']")
    WebElement contactsButton;
    @FindBy(xpath ="//select[@id='langSelect']")
    WebElement languageDropDown;
    @FindBy(xpath = "//input[@formcontrolname='searchInput']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@href='/contacts']")
    WebElement addContactButton;
    @FindBy(xpath = "//*[@id='contacts-list']")
    WebElement contactsList;
    @FindBy(xpath ="//*[@class='list-group']")
    List<WebElement> contactsRows;
    @FindBy(xpath = "//*[@ng-reflect-router-link='/account']")
    WebElement account;
    @FindBy(xpath = "//*[@src='/assets/icons/trash.svg']")
    WebElement deleteButton;
    @FindBy(xpath = "//*[@type='warning']")
    WebElement noResultsMessage;
    @FindBy(xpath = "//*[@text()='Logout']")
    WebElement logoutButton;

    public void waitForLoading(){
        getWait().forVisibility(header);
        getWait().forVisibility(contactsButton);
        getWait().forVisibility(addContactButton);
        getWait().forVisibility(contactsList);
        getWait().forAllVisibility(contactsRows);
        getWait().forClickable(addContactButton);
        getWait().forClickable(contactsButton);
    }

    public void openContactsPage(){
        contactsButton.click();
    }

    public int getContactCount(){
        return driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class='list-group']")).size();
    }

    public AddContactDialog openAddContactDialog(){
        addContactButton.click();
        return new AddContactDialog(driver);
    }

    public void selectLanguage(String language){
        getSelect(languageDropDown).selectByVisibleText(language);
    }

    public String getLanguage(){
        return getSelect(languageDropDown).getFirstSelectedOption().getText();
    }



    public DeleteContactDialog openDeleteDialog(){
        getWait().forClickable(deleteButton);
        deleteButton.click();
        return new DeleteContactDialog(driver);

    }

    public void filterByContact(String contactValue){
        searchInput.sendKeys(contactValue);
    }

    public boolean isNoResultMessageDisplayed(){
        getWait().forVisibility(noResultsMessage);
        return isElementDisplayed(noResultsMessage);
    }

    public void takeScreenshotHeader(){
        takeAndCompareScreenshot("header",header);
    }

    public void takeScreenshotNoResultMessage(){
        takeAndCompareScreenshot("contactsPageNoResultMessage", noResultsMessage);
    }

    }

