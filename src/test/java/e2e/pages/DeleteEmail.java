package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteEmail extends EmailInfoPage {
    public DeleteEmail(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@role='dialog']")
    WebElement dialog;

    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;

    @FindBy(xpath = "//*[@class='dropdown-item btn-email-remove']")
    WebElement removeEmailButton;

    public void waitForOpen(){
        getWait().forVisibility(dialog);
        getWait().forClickable(optionDropDown);
        getWait().forVisibility(removeEmailButton);


    }

    @Override
    public void tabDropDawn() {optionDropDown.click();
        super.tabDropDawn();
    }

    public void setConfirmDeletion(){
        removeEmailButton.click();
    }

    public void removeEmail(){
        getWait().forClickable(removeEmailButton);
        removeEmailButton.click();
        getWait().forInvisibility(dialog);
}
}
