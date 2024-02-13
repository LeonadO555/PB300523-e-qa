package e2e;

import com.github.javafaker.Faker;
import e2e.pages.*;
import e2e.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanEditExistingContactTest extends TestBase {
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
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + " is not equal " + firstName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + " is not equal " + lastName);
        Assert.assertEquals(actualDescription, description, actualDescription + " is not equal " + description);
    }

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void userCanWorkWithContactTest(String firstName, String lastName, String description) {
        String email = "chia.dare@hotmail.com";
        String password = "p5evet4u5yow";
        String language = "English";

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

        // edit contact
        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        //check edited contact
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, editFirstName, editLastName,editDescription);
    }
}
