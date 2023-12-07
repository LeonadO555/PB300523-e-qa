package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesInfoPage extends ContactInfoPage {


    public AddressesInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;
    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addAddressButton;
    @FindBy(xpath = "//*[@class='close']")
    WebElement closeButton;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;
    @FindBy(xpath = "//*[@class='col-country']")
    WebElement countryField;
    @FindBy(xpath = "//*[@class='col-city']")
    WebElement cityField;
    @FindBy(xpath = "//*[@class='col-zip']")
    WebElement postCodeField;
    @FindBy(xpath = "//*[@class='col-street']")
    WebElement streetField;


    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forVisibility(addAddressButton);
        getWait().forVisibility(closeButton);
        getWait().forVisibility(optionDropDown);
        getWait().forVisibility(countryField);
        getWait().forVisibility(cityField);
        getWait().forVisibility(postCodeField);
        getWait().forVisibility(streetField);
    }
    public String getCountry() {
        return countryField.getText();
    }

    public String getCity() {
        return cityField.getText();
    }

    public String getPostCode() {
        return postCodeField.getText();
    }
    public String getStreet() {
        return streetField.getText();
    }
    public void clickOnAddressButton(){
        addAddressButton.click();
    }
}

