package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditEmailDialog extends EmailInfoPage {
    public EditEmailDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailInputField;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;


    public void waitForOpen() {
        getWait().forVisibility(emailInputField);
        getWait().forVisibility(saveButton);
        getWait().forClickable(saveButton);

    }

    public void setEditEmail(String editExpectedEmail) {
        setInput(emailInputField, editExpectedEmail);
    }

    public void saveEmailChanges() {
        saveButton.click();
        getWait().forInvisibility(saveButton);
    }
}
