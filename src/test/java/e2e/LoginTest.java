package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public void userCannotLoginWithInvalidLogin(String email, String password, String caseName) {


        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        loginPage.waitForLoading();
        loginPage.takeLoginPageScreenshot(caseName + "negative_Login_Case");
    }

}

