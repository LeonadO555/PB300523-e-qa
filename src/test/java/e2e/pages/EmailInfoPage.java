package e2e.pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailInfoPage extends ContactInfoPage {
    public EmailInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;
    @FindBy(xpath = "//*[@class='row-table w-95']")
    WebElement emailField;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;
    @FindBy(xpath = "//*[@ng-reflect-type='warning']")
    WebElement noResultMessage;


    public void waitForLoading() {
        getWait().forVisibility(searchInput);
        getWait().forClickable(addEmailButton);

    }

    public void clickOnAddEmailButton() {
        addEmailButton.click();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void clickOnDropdownButtonEdit() {
        getWait().forClickable(optionDropDown);
        optionDropDown.click();
        WebElement editOpen = driver.findElement(By.xpath("//*[text()='Edit']"));
        editOpen.click();
    }
    public void takeEmailInfoPageScreenshot(String actualScreenshotName) {
        takeAndCompareScreenshot(actualScreenshotName, null);
    }

    public void takeScreenshotNoResultMessage() {
        takeAndCompareScreenshot("EmailPageNoResultMessage", noResultMessage);
    }

}
