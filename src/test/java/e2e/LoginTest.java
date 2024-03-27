package e2e;


import e2e.Untils.DataProviders;
import e2e.pages.ContactsPage;
import e2e.pages.LoginPage;
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


    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
    public void userCannotLogin(String email, String password, String caseName){
        //String email = "newtest@gmail.com";
        //String password = "newtest@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);

        loginPage.waitForLoading();
        loginPage.takeLoginPageScreenshot(caseName + "_negative_login_case");
    }
}
