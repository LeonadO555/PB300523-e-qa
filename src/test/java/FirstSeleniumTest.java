import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
    public WebDriver driver;

    @Test
    public void firstSeleniumTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/user/login");
        driver.manage().window().maximize();

    }
}
