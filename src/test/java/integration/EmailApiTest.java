package integration;

import integration.contact.ContactApi;
import integration.email.EmailApi;
import integration.schemas.EmailDto;
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

    private void checkEmailData(int phoneId, EmailDto phoneData) {
        JsonPath actualObject = emailApi.getEmail(200, phoneId).jsonPath();
        LinkedHashMap<String, String> emailObjects = new LinkedHashMap<>();
//        phoneObjects.put(actualObject.getString("countryCode"), phoneData.getCountryCode());
//        phoneObjects.put(actualObject.getString("phoneNumber"), phoneData.getPhoneNumber());

        for (Map.Entry<String, String> phoneObject : emailObjects.entrySet()) {
            String actualResult = phoneObject.getKey();
            String expectedResult = phoneObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }

    @Test
    public void userCanWorkWithEmailApiTest() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        // login as user and get Access token from Response Header
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);

        contactApi = new ContactApi(token); // put Access token to class which need token from requests
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id");

        //create email
        emailApi = new EmailApi(token);
        emailApi.createEmail(201, contactId);
        JsonPath emailObject = emailApi.getAllEmails(200, contactId).jsonPath();
        int emailId = emailObject.getInt("[0].id");
//        checkEmailData(emailId, emailApi.rndDataForCreateEmailAddress(contactId));

        emailApi.editNewEmail(200, contactId, emailId);
//        checkEmailData(emailId, emailApi.rndDataForCreateEmailAddress(emailId, contactId));
//
//        emailApi.deleteEmail(200, emailId);


    }

}
