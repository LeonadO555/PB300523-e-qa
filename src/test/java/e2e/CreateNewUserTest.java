package e2e;

import com.github.javafaker.Faker;
import e2e.pages.AddContactDialog;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import integration.user.UserApi;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateNewUserTest extends TestBase {
    UserApi userApi;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();


    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String contactDescription) {
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualContactDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + " is not equal " + firstName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + " is not equal " + lastName);
        Assert.assertEquals(actualContactDescription, contactDescription, actualContactDescription + " is not equal " + contactDescription);
    }

    @Epic(value = "Registration")
    @Feature(value = "User can registration new user and add new contact")
    @Description(value = "User can registration new user and add contact")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("2")

    @Test
    public void CreateNewUserTest() throws InterruptedException {

        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String language = "English";
        String firstName = faker.lorem().toString();
        String lastName = faker.lorem().toString();
        String contactDescription = faker.lorem().toString();


        userApi = new UserApi();
        String token = userApi.newUserRegistration(email, password, 201);
        System.out.println(token);
        userApi.activation(token, 200);


        contactsPage = new ContactsPage(app.driver);
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);


        contactsPage = new ContactsPage(app.driver);
        Thread.sleep(6000);
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);


        addContactDialog = new AddContactDialog(app.driver);
        addContactDialog = contactsPage.openAddContactDialog();
        //addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName, lastName, contactDescription);
        addContactDialog.saveContact();


        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, firstName, lastName, contactDescription);
    }
}
