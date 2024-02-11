package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageCreateNewUser extends BasePage{
    public LoginPageCreateNewUser(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='btn-link']")
    WebElement registerAccount;
    @FindBy(xpath = "//*[@class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']")
    WebElement emailInput;

    @FindBy(xpath = "//*[@class='form-control rounded-pill ml-auto ng-untouched ng-pristine ng-invalid']")
    WebElement passwordCreate;

    @FindBy(xpath = "//*[@name='confirm-password']")
    WebElement passwordConfirm;

    @FindBy(xpath = "//*[@class='btn btn-info my-1 btn-block']")
    WebElement signUpButton;

    @Step("Wait for loading Login page")
    public void waitForLoading(){
        getWait().forVisibility(registerAccount);
        getWait().forVisibility(emailInput);
        getWait().forVisibility(passwordCreate);
        getWait().forVisibility(passwordConfirm);
        getWait().forVisibility(signUpButton);
    }

    public void takeLoginPageScreenshot(String actualScreenshotName){
        takeAndCompareScreenshot(actualScreenshotName, null);
    }

    @Step("Login as user: {email}, {password}")
    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordCreate.sendKeys(password);
        passwordConfirm.sendKeys(password);
        signUpButton.click();
    }
}

