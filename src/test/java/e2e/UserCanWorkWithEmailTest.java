package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithEmailTest extends TestBase{

    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EmailInfoPage emailInfoPage;
    AddEmailDialog addEmailDialog;

    DeleteEmail deleteEmail;

    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);
    }

    private void checkEmailData(EmailInfoPage page, String email){
        String actualEmailName = page.getEmail();
        Assert.assertEquals(actualEmailName,email,actualEmailName+ "is not equal " + email);

    }
    @Test
    public void userCanWorkWithEmailTest(){
        String email = "newTest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";
        String expectedEmail = "newhrest@gmail.com";


        String firsName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        //logged as user
        loginPage=new LoginPage(app.driver);
        //loginPage.waitForLoading();
        loginPage.login(email,password);
        //check that user was logged
        contactsPage = new ContactsPage(app.driver);
        //contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        String actualLanguage = contactsPage.getLanguage();
        Assert.assertEquals(actualLanguage,language);
        //add contact

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firsName,lastName,description);
        addContactDialog.saveContact();
        //check  create contact
        contactInfoPage=new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage,firsName,lastName,description);
        contactInfoPage.openTab(ContactInfoTabs.EMAILS);


        emailInfoPage = new EmailInfoPage(app.driver);
        emailInfoPage.waitForLoading();

        emailInfoPage.clickOnAddEmailButton();


        addEmailDialog = new AddEmailDialog(app.driver);
        addEmailDialog.waitForLoading();
        addEmailDialog.setEmailInput(expectedEmail);
        addEmailDialog.saveEmail();
        checkEmailData(emailInfoPage,expectedEmail);
        addEmailDialog.tabDropDawn();

        //delete email
        deleteEmail = emailInfoPage.openEmailDelete();
        deleteEmail.waitForLoading();


    }
}
