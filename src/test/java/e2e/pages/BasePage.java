package e2e.pages;

import e2e.Wait.Wait;
import jdk.jshell.spi.ExecutionControl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Wait getWait(){
        return new Wait(driver);
    }

    protected boolean isElementDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public Select getSelect(WebElement element){
        return new Select(element);
    }

    protected void setInput(WebElement input, String value){
        input.click();
        input.clear();
        input.sendKeys(value);
    }

    protected void takeAndCompareScreenshot(String actualScreenshotName,WebElement element) throws IOException {
        String referenceImageFilePath = "reference/" + actualScreenshotName + ".png";
        String tmpFilePath = "reference/tmp_" + actualScreenshotName + ".png";
        File tmp;
        if (element == null){
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } else {
            tmp = element.getScreenshotAs(OutputType.FILE);
        }
        Files.copy(tmp.toPath(),new File(tmpFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

        File referenceImageFile = new File(referenceImageFilePath);
        if(!referenceImageFile.exists()){
            throw new RuntimeException("Reference image file does not exist" + referenceImageFilePath);
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        double maxDiffPercent = 0.01 * width * height;

        ProcessBuilder pb = new ProcessBuilder("compare !!!!","-metric","AE",referenceImageFilePath,tmpFilePath,"null:");
    }
}
