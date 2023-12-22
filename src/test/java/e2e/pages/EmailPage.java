package e2e.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends ContactInfoPage{
    //import constructor!!!
    public EmailPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;


    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forVisibility(addEmailButton);

    }

}

