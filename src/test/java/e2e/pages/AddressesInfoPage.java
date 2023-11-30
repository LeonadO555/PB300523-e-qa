package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesInfoPage extends ContactInfoPage{


    public AddressesInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath ="//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addAddressButton;
    @FindBy(xpath = "//*[@id='input-city']")
    WebElement cityInput;
    @FindBy(xpath = "//*[@id='input-zip']")
    WebElement postCodeInput;
    @FindBy(xpath = "//*[@id='input-street']")
    WebElement streetInput;
    @FindBy(xpath = "//*[@class='btn btn-primary btn-block']")
    WebElement saveButton;
    @FindBy(xpath = "//*[@class='close']")
    WebElement closeButton;
    @FindBy(xpath = "//*[@class=\"dropdown-toggle btn btn-outline-light btn-block\"]]")
    WebElement optionDropDown;
}
