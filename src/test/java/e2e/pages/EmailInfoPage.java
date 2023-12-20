package e2e.pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailInfoPage extends ContactInfoPage{
    public EmailInfoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='search']")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id='btn-add-phone']")
    WebElement addEmailButton;
    @FindBy(xpath = "//*[@class='row-table w-95']")
    WebElement emailField;
    @FindBy(xpath = "//*[@class='dropdown-toggle btn btn-outline-light btn-block']")
    WebElement optionDropDown;



    public void waitForLoading(){
        getWait().forVisibility(searchInput);
        getWait().forClickable(addEmailButton);
        getWait().forVisibility(emailField);
        getWait().forVisibility(optionDropDown);
    }

    public void clickOnAddEmailButton(){
        addEmailButton.click();
    }
    public String getEmail(){
        return emailField.getText();
    }
    public void clickOnDropdownButtonEdit(String Edit){
        getSelect(optionDropDown).selectByVisibleText(Edit);
    }
    public void clickOnDropdownButtonDelete(String Remove){
        getSelect(optionDropDown).selectByVisibleText(Remove);
    }

}
