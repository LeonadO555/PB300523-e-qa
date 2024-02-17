package e2e;

import com.github.javafaker.Faker;
import e2e.pages.AddContactDialog;
import e2e.pages.ContactInfoPage;
import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import integration.user.UserApi;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateNewUsersTest extends TestBase{

    UserApi userApi;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    Faker faker = new Faker();
    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String description){
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName,firstName, actualFirstName + " is not equal " + firstName);
        Assert.assertEquals(actualLastName,lastName, actualLastName + " is not equal " + lastName);
        Assert.assertEquals(actualDescription,description, actualDescription + " is not equal " + description);
    }


    @Epic(value = "Registration")
    @Feature(value= "User can registration new user,add contact")
    @Description(value = "User can registration new user,add contact")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("2")
    @Test
    public void CreateNewUserTest(){
        String email = "new10t@gmail.com";
        String password = "new14t@gmail.com";
        String language = "English";
        String firstName = "Oleksandr";
        String lastName = "Rashevchenko";
        String description = "345345345fdgdf";

        userApi = new UserApi();
        String token = userApi.registration(email,password,201);;
        userApi.activation(token,200);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);

        addContactDialog =new AddContactDialog(app.driver);
        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName,lastName,description);
        addContactDialog.saveContact();

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, firstName,lastName,description);
    }
}
