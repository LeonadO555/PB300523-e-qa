package e2e;

import e2e.TestBase;
import e2e.pages.*;
import e2e.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditCreatedContactOnNewRegisteredUserTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;
    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String description){
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName,firstName, actualFirstName + " is not equal " + firstName);
        Assert.assertEquals(actualLastName,lastName, actualLastName + " is not equal " + lastName);
        Assert.assertEquals(actualDescription,description, actualDescription + " is not equal " + description);
    }
    @Test(dataProvider = "newUserData", dataProviderClass = DataProviders.class)
    public void editCreatedContactOnNewRegisteredUser (String email,String password){
        String language = "English";
        String editedFirstName = "Alex";
        String editedLastName = "Shevchenko";
        String editedDescription = "Edited Contact";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);
        contactsPage.clickOnContactOnNewRegisteredUser();
        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openEditContactForm();
        editContactForm = new EditContactForm(app.driver);
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editedFirstName);
        editContactForm.setLastNameInput(editedLastName);
        editContactForm.setDescriptionInput(editedDescription);
        editContactForm.saveChanges();
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, editedFirstName, editedLastName,editedDescription);
    }
}
