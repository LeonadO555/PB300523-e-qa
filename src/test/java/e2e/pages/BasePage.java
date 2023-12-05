package e2e.pages;

import e2e.wait.Wait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    public WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public Wait getWait(){
        return new Wait(driver);
    }

    public Select getSelect(WebElement element){
        return new Select(element);

    }
    protected boolean isElementDisplayed(WebElement element)  {

        try {
            element.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
    protected void setInput(WebElement input,String value){
        input.click();
        input.clear();
        input.sendKeys(value);
    }
}

