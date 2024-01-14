package e2e;

import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
import e2e.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{
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
    @Test(dataProvider = "invalidLogin",dataProviderClass = DataProviders.class)
    public void userCannotLoginWithInvalidEmail(String email, String password, String caseName){
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        loginPage.takeLoginPageScreenshot(caseName + "_negative_login_case");
        loginPage.waitForLoading();
    }

    private void loginTestMethod(String email, String password, boolean typeOfCase){
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);
        if (typeOfCase){
            loginPage.waitForLoading();
        }else {
            contactsPage = new ContactsPage(app.driver);
            contactsPage.waitForLoading();
        }
    }

    @Test
    public void userCanLoginWithValidData(){
        loginTestMethod("newtest@gmail.com","newtest@gmail.com", false);
    }

    @Test
    public void userCanLoginWithInvalidEmail(){
        loginTestMethod("newtest@gmail.com","newtest@gmail.com", false);
    }

    @Test
    public void userCanLoginWithInvalidEmail(){
        loginTestMethod("newtest@gmail.com","newtest@gmail.com", false);
    }


}
