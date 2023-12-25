package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import e2e.pages.AddContactDialog;
import e2e.pages.AddPhoneDialog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithPhoneTest extends TestBase {

    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    PhoneInfoPage phoneInfoPage;
    AddPhoneDialog addPhoneDialog;
    Faker faker = new Faker();

    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);
    }

        private void checkPhoneData(PhoneInfoPage page,String code, String number){
            String actualCountryCode = page.getCountryCode();
            String actualPhoneNumber = page.getPhoneNumber();
            Assert.assertEquals(actualCountryCode,code,actualCountryCode+ "is not equal " + code);
            Assert.assertEquals(actualPhoneNumber,number,actualPhoneNumber+ "is not equal " + number);
        }

            @Test
            public void userCanWorkWithPhoneTest(){
                String email = "newTest@gmail.com";
                String password = "newtest@gmail.com";
                String language = "English";
                String code = "Germany (+49)";
                String number = "1576533393";


                String firsName = faker.internet().uuid();
                String lastName = faker.internet().uuid();
                String description = faker.lorem().sentence();


                //logged as user
                loginPage=new LoginPage(app.driver);
                //loginPage.waitForLoading();
                loginPage.login(email,password);
                //check that user was logged
                contactsPage = new ContactsPage(app.driver);
                //contactsPage.waitForLoading();
                contactsPage.selectLanguage(language);
                String actualLanguage = contactsPage.getLanguage();
                Assert.assertEquals(actualLanguage,language);
                //add contact

                addContactDialog = contactsPage.openAddContactDialog();
                addContactDialog.waitForOpen();
                addContactDialog.setAddContactForm(firsName,lastName,description);
                addContactDialog.saveContact();
                //check  create contact
                contactInfoPage=new ContactInfoPage(app.driver);
                contactInfoPage.waitForLoading();
                checkContactData(contactInfoPage,firsName,lastName,description);
                contactInfoPage.openTab(ContactInfoTabs.PHONES);

                //addPhoneNumber
                phoneInfoPage = new PhoneInfoPage(app.driver);
                phoneInfoPage.waitForLoading();
                phoneInfoPage.clickOnAddPhoneButton();

                addPhoneDialog = new AddPhoneDialog(app.driver);
                addPhoneDialog.selectCodeCountry(code);
                addPhoneDialog.setPhoneInput(number);
                addPhoneDialog.savePhoneNumber();
                checkPhoneData(phoneInfoPage,code ,number);
        }
    }

