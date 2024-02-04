package integration;

import integration.contact.ContactApi;
import integration.email.EmailApi;
import integration.schemas.EmailDto;
import integration.schemas.PhoneDto;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmailApiTest {
    UserApi userApi;
    ContactApi contactApi;
    EmailApi emailApi;
    private void checkEmailData(int emailId, EmailDto emailData) {
        JsonPath actualObject = emailApi.getEmail(200, emailId).jsonPath();
        LinkedHashMap<String, String> phoneObjects = new LinkedHashMap<>();
        phoneObjects.put(actualObject.getString("email"), emailData.getEmail());


        for (Map.Entry<String, String> emailObject : phoneObjects.entrySet()) {
            String actualResult = emailObject.getKey();
            String expectedResult = emailObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }

    @Test
    public void userCanWorkWithEmailViaApiTest(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        userApi = new UserApi();
        String token = userApi.login(email,password,200);
        contactApi = new ContactApi(token);
        JsonPath contactObject = contactApi.createContact(201).jsonPath();
        int contactId = contactObject.getInt("id");
        emailApi = new EmailApi(token);
        emailApi.createNewEmail(201,contactId);
        JsonPath emailObject = emailApi.getAllEmail(200,contactId).jsonPath();
        int emailId = emailObject.getInt("[0].id");
        checkEmailData(emailId,emailApi.rndForCreatedNewEmail(contactId));

        emailApi.updateEmail(200,emailId,contactId);
        checkEmailData(emailId,emailApi.rndForUpdatedEmail(emailId,contactId));

        emailApi.deleteEmail(200,emailId);
        JsonPath actualDeleteObject = emailApi.getEmail(500, emailId).jsonPath();
        String errorMessage = actualDeleteObject.getString("message");
        Assert.assertEquals(errorMessage, "Error! This email doesn't exist in our DB");
    }
}