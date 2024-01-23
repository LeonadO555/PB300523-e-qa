package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LoginPage extends BasePage{
    // important constructor!!!
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Describe locator
    @FindBy(xpath = "//*[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//*[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement loginButton;

    @Step("Wait for loading login page ")
    public void waitForLoading(){
        getWait().forVisibility(emailInput);
        getWait().forVisibility(passwordInput);
        getWait().forVisibility(loginButton);
    }

    public void takeLoginPageScreenshot(String actualScreenshotName){
        takeAndCompareScreenshot(actualScreenshotName, null);
    }

    @Step("Login as a user: {email}, {password}")
    // Describe methods
    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
