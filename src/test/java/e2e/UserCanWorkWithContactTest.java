package e2e;

import com.github.javafaker.Faker;
import e2e.enums.ContactInfoTabs;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanWorkWithContactTest extends TestBase {
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddContactDialog addContactDialog;
    ContactInfoPage contactInfoPage;
    EditContactForm editContactForm;
    DeleteContactDialog deleteContactDialog;
    AddressesInfoPage addressesInfoPage;
    AddAddressDialog addAddressDialog;
    Faker faker = new Faker();
    private void checkContactData(ContactInfoPage page,String firsName,String lastName,String description){
        String actualFirstName=page.getFirstName();
        String actualLastName=page.getLastName();
        String actualDescription=page.getDescription();
        Assert.assertEquals(actualFirstName,firsName,actualFirstName+ "is not equal "+firsName);
        Assert.assertEquals(actualLastName,lastName,actualLastName+ "is not equal "+lastName);
        Assert.assertEquals(actualDescription,description,actualDescription+ "is not equal "+description);
    }
    private void checkAddressData(AddressesInfoPage page,String country,String city,String postCode,String street){
        String actualCountryName = page.getCountry();
        String actualCityName = page.getCity();
        String actualPostCode = page.getPostCode();
        String actualStreetName = page.getStreet();
        Assert.assertEquals(actualCountryName,country,actualCountryName+ "is not equal "+country);
        Assert.assertEquals(actualCityName,city,actualCityName+ "is not equal "+city);
        Assert.assertEquals(actualPostCode,postCode,actualPostCode+ "is not equal "+postCode);
        Assert.assertEquals(actualStreetName,street,actualStreetName+ "is not equal "+street);

    }
    @Test
    public void userCanWorkWithContactTest() throws InterruptedException {
        String email = "newTest@gmail.com";
        String password = "newtest@gmail.com";
        String language = "English";
        String country = "Germany";
        String city = "Berlin";
        String postCode = "19455";
        String street = "Dresdner st 8";

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().sentence();

        String editFirstName = faker.internet().uuid();
        String editLastName = faker.internet().uuid();
        String editDescription = faker.lorem().sentence();

        //logged as user
        loginPage=new LoginPage(app.driver);
        //loginPage.waitForLoading();
        loginPage.login(email,password);

        //check that user was logged
        contactsPage = new ContactsPage((app.driver));
        contactsPage.waitForLoading();

        //add contact
        addContactDialog = contactsPage.openAddContactDialog();
        addContactDialog.waitForOpen();
        addContactDialog.setAddContactForm(firstName, lastName, description);
        addContactDialog.saveContact();

        //Check created contact
        contactInfoPage = new ContactInfoPage(app.driver);
        contactInfoPage.waitForLoading();
        checkContactData(contactInfoPage, firstName, lastName, description);

        // edit contact
        editContactForm = contactInfoPage.openEditContactForm();
        editContactForm.waitForOpen();
        editContactForm.setFirstNameInput(editFirstName);
        editContactForm.setLastNameInput(editLastName);
        editContactForm.setDescriptionInput(editDescription);
        editContactForm.saveChanges();

        // check edited contact
        checkContactData(contactInfoPage, editFirstName, editLastName, editDescription);
        contactInfoPage.waitForLoading();


        // open contacts page
        contactInfoPage.openContactsPage();
        contactsPage.waitForLoading();

        // filter by contact name
        contactsPage.filterByContact(editFirstName);
        contactsPage.waitForLoading();

        // check rows count after filter by contact
        int actualContactCountRow = contactsPage.getContactCount();
        Assert.assertEquals(actualContactCountRow, 1, "Contact count row after filter should be 1");

        //delete contact
        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

        //check that contact was deleted
        //contactsPage.waitForLoading();
        Assert.assertTrue(contactsPage.isNoResultMessageDisplayed(), "No result message is not visible");
        contactsPage.takeScreenShotNoResultMessage();
    }
}
