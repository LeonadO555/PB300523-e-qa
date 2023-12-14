package e2e;

import com.github.javafaker.Faker;
import e2e.pages.*;
import e2e.utils.DataProviders;
import net.bytebuddy.implementation.bytecode.Throw;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserCanWorkWithContactTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;
    DeleteContactDialog deleteContactDialog;

    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page,String firstName,String lastName,String description){
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + "is not equal " + firstName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + "is not equal " + lastName);
        Assert.assertEquals(actualDescription, description, actualDescription + "is not equal " + description);
    }
    @Test(dataProvider = "newContact",dataProviderClass = DataProviders.class)
    public void userCanWorkWithContactTest(String firstName,String lastName,String description) throws InterruptedException {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

//        String firstName = faker.internet().uuid();
//        String lastName = faker.internet().uuid();
//        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        //login as user
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        // check that user was logged
        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();

        // add contact
        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName,lastName ,description );
        addContactDialog.saveContact();

        // check created contact
        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,firstName,lastName,description);

        // edit contact
        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        // check edited contact
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, editFirstName, editLastName, editDescription);

        // open contacts page
        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();

        // filter by contact name (editFirstName)
        contactsPage.filterByContact(editFirstName);
        contactsPage.waitForLoading();

        //check rows count after filter by contact name
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow,1, "Contact count row after filter should be 1");

        // delete contact
        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        // check that deleted contact was deleted
        contactsPage.waitForLoading();
        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(),"No result message is not visible");
    }
}
