package e2e;

import com.github.javafaker.Faker;
import e2e.Untils.DataProviders;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithContactTest extends TestBase{
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;
    DeleteContactDialog deleteContactDialog;
    Faker  faker = new Faker();
    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String description){
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + "is not equal" + firstName );
        Assert.assertEquals(actualLastName, lastName, actualLastName + "is not equal" + lastName );
        Assert.assertEquals(actualDescription, description, actualDescription + "is not equal" + description );
    }
    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void userCanWorkWithContactTest(String firstName, String lastName, String description) {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";

        //String firstName = faker.internet().uuid();
        //String lastName = faker.internet().uuid();
        //String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        loginPage.waitForLoading();

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName, lastName, description);
        addContactDialog.saveContact();

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, firstName, lastName, description);

        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, editFirstName, editLastName, editDescription);

        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();
        contactsPage.filterByContact(editFirstName);
        contactsPage.waitForLoading();

        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow, 1, "Contact count row after filter should be 1");

        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletionCheckBox();
        deleteContactDialog.removeContact();
        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");
        contactsPage.takeScreenshotNoResultMessage();
    }
}
