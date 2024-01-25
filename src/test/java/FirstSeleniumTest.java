import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FirstSeleniumTest {

    public WebDriver driver;

    @Test
    public void firstSeleniumTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/user/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement emailInput = driver.findElement(By.xpath("//*[@name='email']"));
        emailInput.sendKeys("newtest@gmail.com");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@name='password']"));
        passwordInput.sendKeys("newtest@gmail.com");

        WebElement loginButton = driver.findElement(By.xpath("//*[@type='submit']"));
        loginButton.click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='list-group']"));
        int actualContactCount = elements.size();
        int expectedContactCount = 2315;
        Assert.assertEquals(actualContactCount, expectedContactCount, actualContactCount + " not equal" + expectedContactCount);

        driver.quit();
    }
}