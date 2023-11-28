package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactsPage extends BasePage {
    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']")
    WebElement header;

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//*[@href='/']")
    WebElement contactsButton;

    @FindBy(xpath = "//*[@href='/contacts']")
    WebElement addContactButton;
    @FindBy(xpath = "//select[@id='langSelect']")
    WebElement languageDropdown;
    @FindBy(xpath = "//*[@id='contacts-list']")
    WebElement contactsList;

    @FindBy(xpath = "//*[@formcontrolname='searchInput']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@ng-reflect-router-link='/account']")
    WebElement accountButton;
    @FindBy(xpath = "//*[@src='/assets/icons/trash.svg']")
    WebElement deleteButton;

    @FindBy(xpath = "//*[@type='warning']")
    WebElement noResultsMassage;


    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutButton;

    public boolean confirmLogin() {
        return header.isDisplayed();
    }

    public void openContactPage () {
        contactsButton.click();
    }

    public int getContactCount () {
        return driver.findElements(By.xpath("//*[@class='contacts-list']//*[@class='list.group']")).size();
    }

    public AddContactDialog openAddContactDialog(){
        Assert.assertTrue(isElementDisplayed(addContactButton));
        addContactButton.click();
        return new AddContactDialog(driver);
    }

    public DeleteContactDialog openDeleteDialog(){
        deleteButton.click();
        return new DeleteContactDialog(driver);
    }

    public void filterByContact(String contactValue){
        searchInput.sendKeys(contactValue);
    }

    public  boolean isNoResultMassageDisplayed(){
        return isElementDisplayed(noResultsMassage);
    }


}
