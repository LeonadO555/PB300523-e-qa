package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import e2e.pages.AddContactDialog;
import e2e.pages.AddPhoneDialog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithPhoneTest extends TestBase {

    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhoneInfoPage phoneInfoPage;
    AddPhoneDialog addPhoneDialog;

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
    private void checkPhoneData(PhoneInfoPage page,String code, String number){
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
        loginPage.waitForLoading();
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
        phoneInfoPage = new PhoneInfoPage(app.driver);
        phoneInfoPage.waitForLoading();
        phoneInfoPage.clickOnAddPhoneButton();
        addPhoneDialog = new AddPhoneDialog(app.driver);
        addPhoneDialog.waitForLoading();
        addPhoneDialog.selectCodeCountry(code);
        addPhoneDialog.setPhoneInput(number);
        addPhoneDialog.savePhoneNumber();

        //check Phone Number
        phoneInfoPage = new PhoneInfoPage(app.driver);
        phoneInfoPage.waitForLoading();
        //checkPhoneData(phoneInfoPage,code, number);


        // edit Phone Number
        editPhoneDialog = phoneInfoPage.openEditPhoneDialog();
        editPhoneDialog.waitForOpen();
        editPhoneDialog.selectCountryCode(editCode);
        editPhoneDialog.setEditPhone(editNumber);
        editPhoneDialog.savePhoneChanges();
        phoneInfoPage.waitForLoading();

        //Check edit Phone Number
        //checkPhoneData(phoneInfoPage,code, number);
        phoneInfoPage.waitForLoading();

        //check search form
        phoneInfoPage.filterByPhone(editNumber);
        //PhoneInfoPage.waitForLoading();

        //delete email
        phoneInfoPage.deletePhone();

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
}

