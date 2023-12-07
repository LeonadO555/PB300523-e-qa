package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
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
        contactsPage.waitForLoading();
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
    public void userCannotLoginWithInvalidData(String email, String password) {
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
    }


    @Test
    public void userCannotLoginWithEmptyDate() {
        String email = "";
        String password = "";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
    }

}
