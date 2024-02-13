package e2e;


import e2e.pages.LoginPage;

import e2e.pages.contact.*;
import integration.user.UserApi;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewUserAndContactEdit extends TestBase {
    LoginPage loginPage;
    UserApi userApi;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;


    private void checkContactData(ContactInfoPage page, String firsName, String lastName, String description) {
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firsName, actualFirstName + "is not equal " + firsName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + "is not equal " + lastName);
        Assert.assertEquals(actualDescription, description, actualDescription + "is not equal " + description);
    }
    @Epic(value = "new user with changed data ")
    @Feature(value = "contact data was changet")
    @Description(value =  " Contact info created ")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("1")
    @Test(description = "Work with edited contact")
    public void EditContact(){
        String email = "georgiy123@gmail.com";
        String password = "georgiy123@gmail.com";
        String language = "English";
        String firstName = "Georgiy";
        String lastName = "Manolov";
        String description = "Germany,Berlin";


        String editFirstName = "Artur";
        String editLastName = "Martini";
        String editDescription = "Istanbul,Turkish";


        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);
        loginPage.waitForLoading();

        contactsPage = new ContactsPage(app.driver);
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(),language);

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName,lastName,description);
        addContactDialog.saveContact();

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,firstName,lastName,description);

        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,editFirstName,editLastName,editDescription);
    }
}