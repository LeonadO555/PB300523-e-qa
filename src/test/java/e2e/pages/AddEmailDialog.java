package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmailDialog extends EmailInfoPage{
    public AddEmailDialog(WebDriver driver) {
        super(driver);
    }
@FindBy(xpath = "//*[@id='input-email']")
    WebElement emailInputField;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;

    public void waitForOpen(){
        getWait().forVisibility(emailInputField);
        getWait().forVisibility(saveButton);
    }
    public void waitForClose(){
        getWait().forInvisibility(emailInputField);
        getWait().forInvisibility(saveButton);
    }

    public void setEmail(String email){
        emailInputField.sendKeys(email);
    }
    public void clickOnSaveButton(){
        saveButton.click();
    }

}
