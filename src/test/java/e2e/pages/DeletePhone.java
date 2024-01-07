package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePhone extends PhoneInfoPage{
    public DeletePhone(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;
    @FindBy(xpath = "//*[@ng-reflect-type='warning']")
    WebElement noResultMessage;

    public void waitForLoading(){
        getWait().forVisibility(optionDropDown);
    }

    public void clickOnDropdownButtonRemove() {
        getWait().forClickable(optionDropDown);
        optionDropDown.click();
        WebElement removeOpen = driver.findElement(By.xpath("//*[text()='Remove  ']"));
        removeOpen.click();
    }

    public boolean isNoResultMessageDisplayed(){
        return isElementDisplayed(noResultMessage);
    }
    public void takeScreenshotNoResult(){
        takeAndCompareScreenshot("deletePageNoResultMessage",noResultsMessage);
    }



}
