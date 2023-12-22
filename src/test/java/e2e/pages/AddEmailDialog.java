package e2e.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmailDialog extends EmailPage {
    public AddEmailDialog(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;


    @FindBy(xpath = "//*[@id='input-email']")
    WebElement emailField;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement saveButton;


    public void waitForOpen(){
        getWait().forVisibility(dialog);
        getWait().forVisibility(emailField);
        getWait().forClickable(saveButton);

    }


    public void setEmailFieldInput(String emailField){
      //setInput(emailFieldInput,emailField);
    }



    public void setAddContactForm(String emailField, String emailInput){

       // setEmailField(emailField);
       // setEmailInput(emailInput);
    }

    public void saveEmail(){
        try {
            getWait().forClickable(saveButton);
            saveButton.click();
            getWait().forInvisibility(dialog);
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }

    }

}
