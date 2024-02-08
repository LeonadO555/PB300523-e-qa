package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
import integration.user.UserApi;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;



    @Epic(value = "Login")
    @Feature(value = "User login")
    @Story(value = "User can login with role admin")
    @Description(value = "User can login with role admin")
    @Severity(SeverityLevel.BLOCKER)

    @Test(description = "User can login")
    public void userCanLogin() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
    public void userCannotLogin(String email, String password, String caseName) {
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        loginPage.waitForLoading();
        loginPage.takeLoginPageScreenshot(caseName + "_negative_login_case");
    }

    private void loginTestMethod(String email, String password, String screenshotName, boolean negativeCase) {
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);
        if (negativeCase) {
            loginPage.waitForLoading();loginPage.takeLoginPageScreenshot(screenshotName);
        } else {
            contactsPage = new ContactsPage(app.driver);
            contactsPage.waitForLoading();contactsPage.takeScreenshotHeader();
        }
    }

    @Test
    public void userCanLoginWithValidData() {
        loginTestMethod("newtest@gmail.com", "newtest@gmail.com", null, false);
    }

    @Test
    public void userCanLoginWithInvalidEmail() {
        loginTestMethod("newtestgmail.com", "newtest@gmail.com", "login_invalid_email", true);
    }

    @Test
    public void userCanLoginWithInvalidPassword() {
        loginTestMethod("newtest@gmail.com", "newtestgmail.com", "login_invalid_password", true);
    }

    @Test
    public void userCanLoginWithInvalidEmailAndPassword() {
        loginTestMethod("newtestgmail.com", "newtestgmail.com", "login_invalid_email_and_password", true);
    }

}

