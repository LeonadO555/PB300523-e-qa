package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import integration.user.UserApi;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewUserTest extends TestBase{
    LoginPage loginPage;
    UserApi userApi;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhonesPage phonesPage;
    AddPhoneDialog addPhoneDialog;
    EmailInfoPage emailInfoPage;
    AddEmailDialog addEmailDialog;
    AddressesInfoPage addressesInfoPage;
    AddAddressDialog addAddressDialog;
    DeleteContactDialog deleteContactDialog;
    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page, String firsName, String lastName, String description) {
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firsName, actualFirstName + "is not equal " + firsName);
        Assert.assertEquals(actualLastName, lastName, actualLastName + "is not equal " + lastName);
        Assert.assertEquals(actualDescription, description, actualDescription + "is not equal " + description);
    }

    @Epic(value = "UserNewRegistration")
    @Feature(value = "User can be created")
    @Description(value = "User can be creates." + "Contact info created")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("1")
    @Test(description = "Work with new create user")

    public void workWithNewUser(){
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String language = "English";
        String firstname = "Oksana";
        String lastName = "Povkh";
        String description = "Boston, USA";

        userApi = new UserApi();
        String token = userApi.newUserRegistration(email,password, 201);

        userApi.getNewUserActivation(200, token);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);
        loginPage.waitForLoading();

        contactsPage = new ContactsPage(app.driver);
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);

        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstname, lastName, description);
        addContactDialog.saveContact();

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, firstname, lastName, description);

        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();

        phonesPage.openPhoneButton();

        addPhoneDialog =new AddPhoneDialog(app.driver);
        addPhoneDialog.waitForOpen();
        addPhoneDialog.selectCountryCode(addPhoneDialog.getCountry());
        addPhoneDialog.setPhoneNumberInput("0997812210");
        addPhoneDialog.savePhone();

        phonesPage = new PhonesPage(app.driver);
        //phonesPage.takePhonesPageScreenshot();
        phonesPage.waitForLoading();

        contactInfoPage.openTab(ContactInfoTabs.EMAILS);

    }
}
