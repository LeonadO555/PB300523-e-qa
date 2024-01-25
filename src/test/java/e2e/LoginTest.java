package e2e;


import e2e.pages.BasePage;
import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    ContactsPage contactsPage;

    @Test
    public void userCanLogin(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);

        contactsPage = new ContactsPage(app.driver);

        contactsPage.waitForLoading();
    }

    @Test
    public void userCannotLoginWithInvalidEmail(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);
        loginPage.waitForLogin();
    }
}
