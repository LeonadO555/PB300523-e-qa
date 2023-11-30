package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactsPage extends BasePage {
    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']")
    public WebElement header;

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//*[@href='/']")
    WebElement contactsButton;

    @FindBy(xpath = "//*[@href='/contacts']")
    WebElement addContactButton;

    @FindBy(xpath = "//select[@id='langSelect']")
    WebElement languageDropdown;

    @FindBy(xpath = "//*[@id='contacts-list']")
    WebElement contactsList;

    @FindBy(xpath = "//*[@class='list-group']")
    List<WebElement>  contactRows;

    @FindBy(xpath = "//*[@formcontrolname='searchInput']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@ng-reflect-router-link='/account']")
    WebElement accountButton;

    @FindBy(xpath = "//*[@src='/assets/icons/trash.svg']")
    WebElement deleteButton;

    @FindBy(xpath = "//*[@type='warning']")
    WebElement noResultsMessage;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutButton;

    public void waitForLoading(){
        getWait().forVisibility(header);
        getWait().forVisibility(contactsButton);
        getWait().forVisibility(addContactButton);
        getWait().forVisibility(contactsList);
        getWait().forAllVisibility(contactRows);
        getWait().forClickable(addContactButton);
        getWait().forClickable(contactsButton);
    }

    // открыть страницу контактов
    public void openContactsPage(){
        contactsButton.click();
    }

    // считает/достает ко-во вэб элементов
    public int getContactCount(){
        return driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class='list-group']")).size(); // ищем все элементы (их ко-во)с этим локатором
    }

    // метод кот открывает диалоговое окно контакта, аргументы не нужны, т.к. это метод действия это
    public AddContactDialog openAddContactDialog(){
        addContactButton.click();
        return new AddContactDialog(driver); // после клика мы выводим новый экземпляр класса AddContact Dialog
    }

    // метод удаления контакта
    public DeleteContactDialog openDeleteDialog(){
        getWait().forClickable(deleteButton);
        deleteButton.click();
        return new DeleteContactDialog(driver);
    }

    // метод, кот ввводит данные в серчинг(set - заполнить).
    public void filterByContact(String contactValue){
        searchInput.sendKeys(contactValue);
    }

    public boolean isNoResultMessageDisplayed(){
        return  isElementDisplayed(noResultsMessage);
    }


}
