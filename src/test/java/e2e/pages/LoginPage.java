package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    // important constructor!!
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //describe locator

    @FindBy(xpath = "//*[@name = 'email']")
    WebElement emailImput;
    @FindBy(xpath = "//*[@name = 'password']")
    WebElement passwordImput;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement loginButton;
    public void waitForLoading(){
        getWait().forInvisibility(emailImput);
        getWait().forInvisibility(passwordImput);
        getWait().forInvisibility(loginButton);

    }


    //describe methods

    public void login(String email, String password) {
        emailImput.sendKeys(email);
        passwordImput.sendKeys(password);
        loginButton.click();
    }


}
