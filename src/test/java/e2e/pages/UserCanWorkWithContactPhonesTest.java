package e2e.pages;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.enums.ContactInfoTabs;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithContactPhonesTest extends TestBase {

    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhonesPage phonesPage;
    EditContactPhone editContactPhone;
    EditContactForm editContactForm;
    Faker  faker = new Faker();
    private void checkContactData(ContactInfoPage page, String firstName, String lastName, String description){
        String actualFirstName = page.getFirstName();
        String actualLastName = page.getLastName();
        String actualDescription = page.getDescription();
        Assert.assertEquals(actualFirstName, firstName, actualFirstName + "is not equal" + firstName );
        Assert.assertEquals(actualLastName, lastName, actualLastName + "is not equal" + lastName );
        Assert.assertEquals(actualDescription, description, actualDescription + "is not equal" + description );
    }
    @Test
    public void userCanWorkWithContactPhonesTest() throws InterruptedException {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        loginPage.waitForLoading();

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

        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        contactInfoPage.openTab(ContactInfoTabs.PHONES);

        phonesPage = new PhonesPage(app.driver);
        phonesPage.waitForLoading();


    }


}


