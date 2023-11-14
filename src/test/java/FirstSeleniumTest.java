import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
    public WebDriver driver;
    @Test
    public void firstSeleniumTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/user/login");
        driver.manage().window().maximize();

        //name
        WebElement elementVariantNameOne = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement elementVariationNameTwo = driver.findElement(By.cssSelector("[name='email']"));
        WebElement elementVariationNameThree = driver.findElement(By.name("email"));
        //id
        WebElement elementVariantIdOne = driver.findElement(By.xpath("//*[@id='login-form']//*[@id='defaultRegisterFormEmail']"));
        WebElement elementVariationIdTwo = driver.findElement(By.cssSelector("[id='defaultRegisterFormEmail']"));
        WebElement elementVariationIdThree = driver.findElement(By.cssSelector("#defaultRegisterFormEmail"));
        WebElement elementVariationIdFour = driver.findElement(By.id("defaultRegisterFormEmail"));

        //class - лучше не брать( динамические атрибуты: перегразить страницу, зайти инкогнито..
        WebElement elementVariantClassOne = driver.findElement(By.xpath("//*[@id='login-form']//*[@class='form-control mb-2 " +
                "rounded-pill ng-untouched ng-pristine ng-invalid']"));
        WebElement elementVariantClassTwo = driver.findElement(By.xpath("//*[contains(@class, 'form-control mb-2')]"));
        WebElement elementVariantClassThree = driver.findElement(By.cssSelector("[class='form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid']"));
        WebElement elementVariantClassFour = driver.findElement(By.cssSelector(".form-control.mb-2.rounded-pill.ng-untouched.ng-pristine.ng-invalid"));
        WebElement elementVariantClassFive = driver.findElement(By.className("form-control mb-2 rounded-pill ng-untouched ng-pristine ng-invalid"));



        //password name
        WebElement elementVariantPasswordNameOne = driver.findElement(By.xpath("//*[@name='password']"));
        WebElement elementVariationPasswordNameTwo = driver.findElement(By.cssSelector("[name='password']"));
        WebElement elementVariationPasswordNameThree = driver.findElement(By.name("password"));
        //password id
        WebElement elementVariantPasswordIdOne = driver.findElement(By.xpath("//*[@name='password']"));
        WebElement elementVariationPasswordIdTwo = driver.findElement(By.cssSelector("[name='password']"));
        WebElement elementVariationPasswordIdThree = driver.findElement(By.name("password"));
        //password class
        WebElement elementVariantPasswordClassTwo = driver.findElement(By.xpath("//*[contains(@class, 'form-control')]"));
        WebElement elementVariantPasswordClassThree = driver.findElement(By.cssSelector("[class='form-control rounded-pill ng-pristine ng-invalid ng-touched']"));
        WebElement elementVariantPasswordClassFour = driver.findElement(By.cssSelector(".form-control rounded-pill ng-pristine ng-invalid ng-touched"));
        WebElement elementVariantPasswordClassFive = driver.findElement(By.className("form-control rounded-pill ng-pristine ng-invalid ng-touched"));

        driver.quit();
    }
}
