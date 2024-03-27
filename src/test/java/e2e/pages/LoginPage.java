package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    //important constructor!!!
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Described locator
    @FindBy(xpath = "//*[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//*[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement loginButton;

    public void waitForLoading(){
        getWait().forVisibility(emailInput);
        getWait().forVisibility(passwordInput);
        getWait().forVisibility(loginButton);
    }

    public void takeLoginPageScreenshot(String actualScreenshotName) {
        takeAndCompareScreenshot(actualScreenshotName, null);
    }

    //Describe methods

    public void login(String email, String password){

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

    }
}
