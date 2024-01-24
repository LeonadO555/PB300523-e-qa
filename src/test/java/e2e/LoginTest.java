package e2e;


import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;

    @Epic(value = "Login")
    @Feature(value = "User login")
    @Story(value = "User can login with role admin")
    @Description(value = "User can login with role admin ")

    @Test
    public void userCanLogin(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);

        contactsPage = new ContactsPage(app.driver);
        contactsPage.waitForLoading();
    }
    @Test(dataProvider = "userCanNotLoginTest",dataProviderClass = DataProviders.class)
    public void userCannotLoginWithInvalidEmail(String email,String password, String caseName) {
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        loginPage.waitForLoading();
        loginPage.takeLoginPageScreenshot(caseName + "_negative_login_case");
    }

    @Test
    public void userCannotLoginWithInvalidPassword(){
        String email = "newtest@gmail.com";
        String password = "invalid@gmail.com";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);
        loginPage.waitForLoading();
    }
    @Test
    public void userCannotLoginWithInvalidEmailAndPassword(){
        String email = "invalid@gmail.com";
        String password = "invalid@gmail.com";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);
        loginPage.waitForLoading();
    }
    @Test
    public void userCannotLoginWithInvalidPasswordAndEmail() {
        String email = "newtesttt@gmail.com";
        String password = "invalidPassword123";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email,password);
        loginPage.waitForLoading();
    }
}

