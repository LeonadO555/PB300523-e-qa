package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    //import constructor!!!
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Describe locator
    @FindBy(xpath = "//*[@name='user-name']")
    WebElement userNameInput;

    @FindBy(xpath = "//*[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement loginButton;

    public void waitForLoading(){
        getWait().forVisibility(userNameInput);
        getWait().forVisibility(passwordInput);
        getWait().forVisibility(loginButton);
    }

    //Describe methods
    public void login(String email, String password){
        userNameInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }


}
