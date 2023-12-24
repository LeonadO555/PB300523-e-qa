package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePhone extends PhoneInfoPage{
    public DeletePhone(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addPhoneButton;
    @FindBy(xpath = "//*[@class='row-table-pn']")
    WebElement phoneNumbers;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;

    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addPhoneButton);
    }

    public void clickOnDropdownButtonRemove() {
        getWait().forClickable(optionDropDown);
        optionDropDown.click();
        WebElement editOpen = driver.findElement(By.xpath("//*[text()='Remove']"));
        editOpen.click();
    }


}
