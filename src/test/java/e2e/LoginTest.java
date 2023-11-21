package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage LoginPage;
    ContactsPage contactsPage;

    @Test
    public void userCanLogin() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        LoginPage = new LoginPage(app.driver);
        LoginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        Assert.assertTrue(contactsPage.confirmlogin(), "User is not logged");
    }

    @Test
    public void userCannotWithInvalidEmail() {
        String email = "newtestgmail.com";
        String password = "newtest@gmail.com";

        LoginPage = new LoginPage(app.driver);
        LoginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        Assert.assertFalse(contactsPage.confirmlogin(), "User is logged");
    }

    @Test
    public void userCannotWithInvalidPassword() {
        String email = "newtest@gmail.com";
        String password = "newtestgmail.com";

        LoginPage = new LoginPage(app.driver);
        LoginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        Assert.assertFalse(contactsPage.confirmlogin(), "User is logged");
    }
}
