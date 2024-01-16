package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {
    public WebDriver driver;

    protected void init(boolean useSelenoid) throws MalformedURLException {
        if (useSelenoid) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("latest");

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);

            capabilities.setCapability("selenoid:options", selenoidOptions);

            driver = new RemoteWebDriver(
                URI.create("http://165.227.145.48:4444/wd/hub").toURL(),
                capabilities
            );
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void stop(){
        driver.quit();
    }
}
