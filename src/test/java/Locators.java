import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Locators {
    public WebDriver driver;
        // name
        WebElement elementVariantNameOne = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement elementVariantNameTwo = driver.findElement(By.cssSelector("[name='email']"));
        WebElement elementVariantNameThree = driver.findElement(By.name("email"));
        // id
        WebElement elementVariantIdOne = driver.findElement(By.xpath("//*[@id='login-form']//*[@id='defaultRegisterFormEmail']"));
        WebElement elementVariantIdTwo = driver.findElement(By.cssSelector("[id='defaultRegisterFormEmail']"));
        WebElement elementVariantIdThree = driver.findElement(By.cssSelector("#defaultRegisterFormEmail"));
        WebElement elementVariantIdFour = driver.findElement(By.id("defaultRegisterFormEmail"));
        // class bad pract
        WebElement elementVariantClassOne = driver.findElement(By.xpath("//*[@id='login-form']//*[@class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']"));
        WebElement elementVariantClassFive = driver.findElement(By.xpath("//*[contains(@class,'form-control mb-2')]"));
        WebElement elementVariantClassTwo = driver.findElement(By.cssSelector("[class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']"));
        WebElement elementVariantClassThree = driver.findElement(By.cssSelector(".form-control.mb-2.rounded-pill.ng-untouched.ng-pristine.ng-invalid"));
        WebElement elementVariantClassFour = driver.findElement(By.className("form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid"));
        //password
        WebElement elementPassword = driver.findElement(By.xpath("//*[@type='password']"));
        WebElement elementPasswordThree = driver.findElement(By.cssSelector("[placeholder='Password']"));
        WebElement elementPasswordFour = driver.findElement(By.cssSelector("[formcontrolname='password']"));
        WebElement elementPasswordFive = driver.findElement(By.xpath("//*[@formcontrolname='password']"));
        //button Login
        WebElement elementLogin = driver.findElement(By.xpath("//*[@type='submit']"));
    }

