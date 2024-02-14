package e2e;

import com.github.javafaker.Faker;
import e2e.pages.*;
import integration.user.UserApi;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanEditExistingContact extends TestBase {

    UserApi userApi;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;

    Faker faker = new Faker();
    private DeleteContactDialog deleteContactDialog;

    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String contactDescription) {
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualContactDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + " is not equal " + firstName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + " is not equal " + lastName);
        Assert.assertEquals(actualContactDescription, contactDescription, actualContactDescription + " is not equal " + contactDescription);
    }

    @Epic(value = "User with changed data")
    @Feature(value = "Contact data was changet")
    @Description(value = " Contact info created")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("3")
    @Test(description = "Work with edited existing contact")
    public void EditContact() {
        String email = "chia.dare@hotmail.com";
        String password = "p5evet4u5yow";
        String language = "English";
        String firstName = "Nata";
        String lastName = "Koval";
        String description = "Austria,Wien";

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

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
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");
        contactsPage.takeScreenshotNoResultMessage();
    }
}


