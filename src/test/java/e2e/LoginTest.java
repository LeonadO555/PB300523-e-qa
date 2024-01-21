package e2e;


import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;

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


        loginPage.takeLoginPageScreenshot( caseName + "negativeLoginCase");


        contactsPage = new ContactsPage(app.driver);
        contactsPage.getWait().forInvisibility(contactsPage.header);
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

