package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import e2e.pages.AddContactDialog;
import e2e.pages.AddPhonesDialog;
import integration.contact.ContactApi;
import integration.user.UserApi;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithPhoneTest extends TestBase {

    LoginPage loginPage;
    UserApi userApi;
    ContactsPage contactsPage;
    ContactApi contactApi;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhonesPage phonesPage;
    AddPhonesDialog addPhoneDialog;
    EditPhoneDialog editPhoneDialog;
    DeleteContactDialog deleteContactDialog;

    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);
    }
    private void checkPhoneData(PhonesPage page,String code, String number){
        String actualCountryCode = page.getCountryCode();
        String actualPhoneNumber = page.getPhoneNumber();
        Assert.assertEquals(actualCountryCode,code,actualCountryCode+ "is not equal " + code);
        Assert.assertEquals(actualPhoneNumber,number,actualPhoneNumber+ "is not equal " + number);
    }

    @Test
    public void userCanWorkWithPhoneTest(){
        String email = "newTest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";
        String code = "Germany (+49)";
        String number = "1576533393";

        String editCode = "Angola (+244)";
        String editNumber = "1576633293";


        String firsName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();


        //logged as user
        loginPage=new LoginPage(app.driver);
        //loginPage.waitForLoading();
        loginPage.login(email,password);
        //check that user was logged
        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        String actualLanguage = contactsPage.getLanguage();
        Assert.assertEquals(actualLanguage,language);
        //add contact

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firsName,lastName,description);
        addContactDialog.saveContact();
        //check  create contact
        contactInfoPage=new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,firsName,lastName,description);
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        //add Phone Number
        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();
        phonesPage.clickOnAddPhoneButton();
        addPhoneDialog = new AddPhonesDialog(app.driver);
        addPhoneDialog.waitForLoading();
        addPhoneDialog.selectCodeCountry(code);
        addPhoneDialog.setPhoneInput(number);
        addPhoneDialog.savePhoneNumber();

        //check Phone Number
        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();
        //checkPhoneData(phoneInfoPage,code,number);

        // edit Phone Number
        editPhoneDialog = phonesPage.openEditPhoneDialog();
        editPhoneDialog.waitForOpen();
        editPhoneDialog.selectCountryCode(editCode);
        editPhoneDialog.setEditPhone(editNumber);
        editPhoneDialog.savePhoneChanges();
        phonesPage.waitForLoading();

        //Check edit Phone Number
        //checkPhoneData(phoneInfoPage,editCode, editNumber);
        phonesPage.waitForLoading();

        //check search form
        phonesPage.filterByPhone(editNumber);
        //PhoneInfoPage.waitForLoading();

        //delete email
        phonesPage.deletePhone();

        //open contacts page
        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();
        //filter by contact name
        contactsPage.filterByContact(firsName);
        contactsPage.waitForLoading();

        //check row
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow, 1, "Contact count row after filter should be 1");

        //delete contact
        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        //check that contact was deleted
        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");
        contactsPage.takeScreenshotNoResultMessage();
    }

    @Epic(value = "Contact")
    @Feature(value = "User can Add edit delete phone")
    @Description(value = "User can Add edit delete phone for new contact")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Work with phone for new contact")
    public void workWithPhoneForNewContact(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        String language = "English";
        String code = "Germany (+49)";
        String number = "1576533393";

        String editCode = "Angola (+244)";
        String editNumber = "1576633293";

        userApi = new UserApi();
        String token= userApi.login(email,password,200);

        contactApi = new ContactApi(token);
        JsonPath json = contactApi.createContact(201).jsonPath();
        int contactId = json.getInt("id");

        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        app.driver.get("http://phonebook.telran-edu.de:8080/contacts/"+contactId);

        contactInfoPage = new ContactInfoPage(app.driver);
        contactsPage.waitForLoading();
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();
        phonesPage.takePhonesPageScreenshot();
        phonesPage.clickOnAddPhoneButton();
        addPhoneDialog = new AddPhonesDialog(app.driver);
        addPhoneDialog.waitForLoading();
        addPhoneDialog.selectCodeCountry(code);
        addPhoneDialog.setPhoneInput(number);
        addPhoneDialog.savePhoneNumber();

        //check Phone Number
        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();
        //checkPhoneData(phoneInfoPage,code,number);

        // edit Phone Number
        editPhoneDialog = phonesPage.openEditPhoneDialog();
        editPhoneDialog.waitForOpen();
        editPhoneDialog.selectCountryCode(editCode);
        editPhoneDialog.setEditPhone(editNumber);
        editPhoneDialog.savePhoneChanges();
        phonesPage.waitForLoading();

        phonesPage.waitForLoading();


        phonesPage.filterByPhone(editNumber);
        //PhoneInfoPage.waitForLoading();

        //delete email
        phonesPage.deletePhone();

        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");
        contactsPage.takeScreenshotNoResultMessage();
    }
}

