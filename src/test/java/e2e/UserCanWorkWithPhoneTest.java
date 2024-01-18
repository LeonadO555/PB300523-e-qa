package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithPhoneTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;
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

        // open contact page
        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();

        // filter by contact name
        contactsPage.filterByContact(firstName);
        contactsPage.waitForLoading();

        // check rows count after filter by contact name
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow, 1, "Contact count row after filter should be 1");

        // check that contact was deleted
        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), " No result message is not visible");
        contactsPage.takeScreenshotNoResultMessage();
    }
}