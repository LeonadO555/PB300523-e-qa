package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DeleteContactDialog extends ContactsPage{
    public DeleteContactDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@id='check-box-remove-contact']")
    WebElement confirmDeletionCheckBox;

    @FindBy(xpath = "//*[@id='submit-remove']")
    WebElement removeContactButton;

    public void waitForOpen(){
        getWait().forInvisibility(confirmDeletionCheckBox);
        getWait().forVisibility(removeContactButton);
    }

    public void setConfirmDeletionCheckBox(){
        Assert.assertTrue(isElementDisplayed(dialog));
        confirmDeletionCheckBox.click();

    }

    public void removeContact() throws InterruptedException {
        getWait().forClickable(removeContactButton);
        removeContactButton.click();
        getWait().forInvisibility(dialog);
    }
}
