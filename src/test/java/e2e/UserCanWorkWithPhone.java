package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithPhone extends TestBase{
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhoneInfoPage phoneInfoPage;
    AddPhoneDialog addPhoneDialog;
    EditPhone editPhone;
    DeletePhone deletePhone;
    Faker faker = new Faker();
    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);
    }

    private void checkPhoneData(PhoneInfoPage page,String phoneInput){
        String actualPhoneNumber = page.getPhone();
        Assert.assertEquals(actualPhoneNumber,phoneInput,actualPhoneNumber+ "is not equal "+phoneInput);
    }

    @Test
    private void userCanWorkWithPhoneNumber(){
        String email = "newTest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";
        String phoneNumberInput = "0966451638";
        String changedPhone = "0965544331";
        String countryCode = "Ukraine (+380)";

        String firsName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        String actualLanguage = contactsPage.getLanguage();
        Assert.assertEquals(actualLanguage,language);

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(lastName,firsName,description);
        addContactDialog.saveContact();

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        phoneInfoPage = new PhoneInfoPage(app.driver);
        phoneInfoPage.waitForLoading();
        phoneInfoPage.clickOnAddPhoneButton();

        addPhoneDialog = new AddPhoneDialog(app.driver);
        addPhoneDialog.waitForOpen();
        addPhoneDialog.selectCountryCode(countryCode);
        addPhoneDialog.setPhoneNumber(phoneNumberInput);
        addPhoneDialog.clickOnSaveButton();
        phoneInfoPage.takeScreenshotNumber();

        checkPhoneData(phoneInfoPage,phoneNumberInput);
        phoneInfoPage.clickOnDropdownButtonEdit();

        editPhone = new EditPhone(app.driver);
        editPhone.waitForOpen();
        editPhone.setPhoneNumberInput(changedPhone);
        editPhone.takeScreenshotEditedNumber();
        editPhone.saveChange();
        checkPhoneData(phoneInfoPage,changedPhone);


        deletePhone = new DeletePhone(app.driver);
        deletePhone.clickOnDropdownButtonRemove();
        Assert.assertTrue(deletePhone.isNoResultMessageDisplayed(),"No result message is not visible");
        deletePhone.takeScreenshotNoResult();

    }
}
