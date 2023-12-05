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
    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);

    }
    @Test
    public void userCanWorkWithContactTest() throws InterruptedException {
        String email = "newTest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";

        String firsName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        //logged as user
        loginPage=new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);
        //check that user was logged
        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        String actualLanguage = contactsPage.getLanguage();
        Assert.assertEquals(actualLanguage,language);
        //add contact

        addContactDialog=contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firsName,lastName,description);
        addContactDialog.saveContact();
        //check  create contact
        contactInfoPage=new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,firsName,lastName,description);


        // edit contact
        editContactForm=contactInfoPage.openEditContactForm();
        editContactForm.waitForLoading();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();



        //check edited contact
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,editFirstName,editLastName,editDescription);

        //open contacts page
        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();
        //find contact by firstname
        contactsPage.filterByContact(editFirstName);
        contactsPage.waitForLoading();
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow,1,"Contact count row after filter should be 1 ");

        //delete contact
        deleteContactDialog=contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(),"No result message is not visible");


    }

}
