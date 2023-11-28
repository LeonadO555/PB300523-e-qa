package e2e;

import com.github.javafaker.Faker;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithContactTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;
    DeleteContactDialog deleteContactDialog;
    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String description) {

        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + " is not equal" + firstName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + " is not equal" + lastName);
        Assert.assertEquals(actualDescription, description, actualDescription + " is not equal" + description);
    }


    @Test
    public void userCanWorkWithContactTest() throws InterruptedException {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        //login as user
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        //check that user was logged
        contactsPage = new ContactsPage((app.driver));
        Assert.assertTrue(contactsPage.confirmLogin());

        //add contact
        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.setAddContactForm(firstName, lastName, description);
        addContactDialog.saveContact();

        //Check created contact
        contactInfoPage = new ContactInfoPage(app.driver);
        Thread.sleep(2000);
        checkContactData(contactInfoPage, firstName, lastName, description);

        // edit contact
        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        // check edited contact
        checkContactData(contactInfoPage, editFirstName, editLastName, editDescription);
        // open contacts page
        contactInfoPage.openContactsPage();
        // filter by contact name
        contactsPage.filterByContact(editFirstName);
        Thread.sleep(2000);
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow, 1, "Contact count row after filter should be 1");

        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();
        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");

    }
}
