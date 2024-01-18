import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Locator {
    public WebDriver driver;


    //name
    WebElement elementVariantNameOne = driver.findElement(By.xpath("//*[@name='email']"));
    WebElement elementVariantNameTwo = driver.findElement(By.cssSelector("[name='email']"));
    WebElement elementVariantNameThree = driver.findElement(By.name("email"));

    //id
    WebElement elementVariantIdOne = driver.findElement(By.xpath("//*[@id='login-form']//*[@id='defaultRegisterFormEmail']"));
    WebElement elementVariantIdTwo = driver.findElement(By.cssSelector("[id='defaultRegisterFormEmail']"));
    WebElement elementVariantIdThree = driver.findElement(By.cssSelector("#defaultRegisterFormEmail"));
    WebElement elementVariantIdFour = driver.findElement(By.id("defaultRegisterFormEmail"));

    //class - bad practice
    WebElement elementVariantClassOne = driver.findElement(By.xpath("//*[@id='login-form']//*//*[@class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']"));
    WebElement elementVariantClassTwo = driver.findElement(By.xpath("//*[contains(@class, 'form-control mb-2')]"));
    WebElement elementVariantClassThree = driver.findElement(By.cssSelector("[class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']"));
    WebElement elementVariantClassFour = driver.findElement(By.cssSelector(".form-control.mb-2.rounded-pill.ng-untouched.ng-pristine.ng-invalid"));
    WebElement elementVariantClassFive = driver.findElement(By.className("form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid"));





    WebElement elementVariantPasswordOne = driver.findElement(By.xpath("//*[@formcontrolname='password']"));
    WebElement elementVariantPasswordTwo = driver.findElement(By.cssSelector("[formcontrolname='password]"));


    WebElement elementVariantPlaceholderOne = driver.findElement(By.xpath("//*[@placeholder='Password']"));
    WebElement elementVariantPlaceholderTwo = driver.findElement(By.cssSelector("[placeholder='Password']"));


    WebElement elementVariantName1One = driver.findElement(By.xpath("//*[@name='password']"));
    WebElement elementVariantName2Two = driver.findElement(By.cssSelector("[name='password']"));
    WebElement elementVariantName3Three = driver.findElement(By.name("password"));
    }