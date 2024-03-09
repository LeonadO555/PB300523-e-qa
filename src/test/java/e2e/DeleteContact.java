package e2e;

import e2e.pages.LoginPage;
import e2e.pages.contact.ContactsPage;
import e2e.pages.contact.DeleteContactDialog;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContact extends TestBase {

    LoginPage loginPage;
    ContactsPage contactsPage;
    DeleteContactDialog deleteContactDialog;

    @Epic(value = "contact delete ")
    @Feature(value = "contact ist deleted")
    @Description(value = " Contact ist deleted ")
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("1")
    @Test(description = "Work with contact deleted")
    public void ContactDelete() {
        String email = "georgiy123@gmail.com";
        String password = "georgiy123@gmail.com";
        String language = "English";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);
        loginPage.waitForLoading();

        contactsPage = new ContactsPage(app.driver);
        contactsPage.selectLanguage(language);
        Assert.assertEquals(contactsPage.getLanguage(), language);

        deleteContactDialog = contactsPage.openDeleteDialog();
        deleteContactDialog.waitForOpen();
        deleteContactDialog.setConfirmDeletion();
        deleteContactDialog.removeContact();

    }
}
