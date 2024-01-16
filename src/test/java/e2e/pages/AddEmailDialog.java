package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmailDialog extends EmailInfoPage {
    public AddEmailDialog(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;
    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailFieldInput;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;
    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement closeWindowsButton;


    public void waitForOpen() {
        getWait().forVisibility(dialog);
        getWait().forVisibility(emailFieldInput);
        getWait().forClickable(saveButton);
        getWait().forClickable(closeWindowsButton);

    }

    public void setEmailInput(String email) {
        emailFieldInput.sendKeys(email);
    }

    public void saveEmailButtonClick() {
        saveButton.click();
    }

}