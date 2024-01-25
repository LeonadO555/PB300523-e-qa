package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import intagration.contact.ContactApi;
import intagration.user.UserApi;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Serial;

public class UserCanWorkWithPhoneTest extends TestBase {
    LoginPage loginPage;
    UserApi userApi;
    ContactsPage contactsPage;
    ContactApi contactApi;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhonesPage phonesPage;
    AddPhoneDialog addPhoneDialog;
    EditPhoneForm editPhoneForm;
    DeleteContactDialog deleteContactDialog;


    Faker faker = new Faker();

    private void checkPhoneData(PhonesPage page, String country, String phoneNumber) {
        phonesPage = new PhonesPage(app.driver);
        String actualCountry = page.getCountry();
        String actualPhoneNumber = page.getPhoneNumber();
        Assert.assertEquals(actualCountry, country, actualCountry + "is not equal" + country);
        Assert.assertEquals(actualPhoneNumber, phoneNumber, actualPhoneNumber + "is not equal" + phoneNumber);

    }

    @Test
    public void userCanAddPhoneNumber() throws InterruptedException {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";
        String country = "Albania";

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        // login as user
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        // check that user was logged
        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);

        // Add contact
        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName, lastName, description);
        addContactDialog.saveContact();

        // open Phone Tab
        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        // add phone number
        phonesPage = new PhonesPage(app.driver);
        //phonesPage.waitForLoading();
        phonesPage.openPhoneButton();

        //fill addPhoneDialog
        addPhoneDialog = new AddPhoneDialog(app.driver);
        addPhoneDialog.waitForOpen();
        addPhoneDialog.selectCountryCode(addPhoneDialog.getCountry());
        addPhoneDialog.setPhoneNumberInput("12345678911");
        addPhoneDialog.savePhone();


        // check created phone
        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();
        checkPhoneData(phonesPage, phonesPage.getCountry(), phonesPage.getPhoneNumber());


        //
        editPhoneForm = phonesPage.openEditPhoneForm();
        editPhoneForm.waitForOpen();
        editPhoneForm.selectCountryCode(editPhoneForm.getCountry());
        editPhoneForm.setPhoneNumberInput("11987654321");
        editPhoneForm.saveChange();
        phonesPage.waitForLoading();

        //
        phonesPage.deletePhone();
    }


    @Epic(value = "Contact")
    @Feature(value = "User can Add, edit, delete phone")
    @Description(value = "User can Add edit delete phone for new contact")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Work with phone for new contact")
    public void workWithPhoneForNewContact() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);

        contactApi = new ContactApi(token);
        JsonPath json = contactApi.createContact(201).jsonPath();
        int contactId = json.getInt("id");

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        app.driver.get("http://phonebook.telran-edu.de:8080/contacts/" + contactId);

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        phonesPage = new PhonesPage(app.driver);
        //phonesPage.waitForLoading();
        phonesPage.openPhoneButton();

        //fill addPhoneDialog
        addPhoneDialog = new AddPhoneDialog(app.driver);
        addPhoneDialog.waitForOpen();
        addPhoneDialog.selectCountryCode(addPhoneDialog.getCountry());
        addPhoneDialog.setPhoneNumberInput("12345678911");
        addPhoneDialog.savePhone();


        // check created phone
        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();

        checkPhoneData(phonesPage, phonesPage.getCountry(), phonesPage.getPhoneNumber());


        //
        editPhoneForm = phonesPage.openEditPhoneForm();
        editPhoneForm.waitForOpen();
        editPhoneForm.selectCountryCode(editPhoneForm.getCountry());
        editPhoneForm.setPhoneNumberInput("11987654321");
        editPhoneForm.saveChange();
        phonesPage.waitForLoading();

        //
        phonesPage.deletePhone();
        contactApi.deleteContact(200, contactId );
    }


}