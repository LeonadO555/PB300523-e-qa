package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;

    @Test
    public void userCanLogin() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        Assert.assertTrue(contactsPage.confirmLogin(), "User can't login");
    }
    @Test
    public void userCannotLoginWithInvalidEmail() {
        String email = "newtesst@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        Assert.assertFalse(contactsPage.confirmLogin(), "User is logged");
    }
    @Test
    public void userCannotLoginWithInvalidPassword(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);
        contactsPage = new ContactsPage(app.driver);
        Assert.assertFalse(contactsPage.confirmLogin(),"User is logged");
    }

}
