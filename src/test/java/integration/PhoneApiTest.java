package integration;

import integration.contact.ContactApi;
import integration.phone.PhoneApi;
import integration.schemas.ContactDto;
import integration.schemas.PhoneDto;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhoneApiTest {
    UserApi userApi;
    ContactApi contactApi;
    PhoneApi phoneApi;

    private void checkPhoneData(int phoneId, PhoneDto phoneData) {

        JsonPath actualObjects = phoneApi.getPhone(200, phoneId).jsonPath();
        LinkedHashMap<String, String> phoneObjects = new LinkedHashMap<>();
        phoneObjects.put(actualObjects.getString("countryCode"), phoneData.getCountryCode());
        phoneObjects.put(actualObjects.getString("phoneNumber"), phoneData.getPhoneNumber());
        for (Map.Entry<String, String> contactObject : phoneObjects.entrySet()) {
            String actualResult = contactObject.getKey();
            String expectedResult = contactObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }

    @Test
    public void userCanWorkWithPhoneViaApiTest() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        // login as user and get Access token from Response Header
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);
        contactApi = new ContactApi(token);
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id");

        // create phone
        phoneApi = new PhoneApi(token);
        phoneApi.createNewPhone(201, contactId);

        JsonPath phoneObject = phoneApi.getAllPhones(200, contactId).jsonPath();
        int phoneId = phoneObject.getInt("[0].id");
        checkPhoneData(phoneId, phoneApi.rndForCreatedNewPhone(contactId));
// update phone
        phoneApi.editNewPhone(200, contactId, phoneId);
        checkPhoneData(phoneId, phoneApi.rndForEditNewPhone(phoneId, phoneId));
        // delete phone
        phoneApi.deletePhone(200, phoneId);
        JsonPath actualDeletedObject = phoneApi.getPhone(500, phoneId).jsonPath();
        String errorMessage = actualDeletedObject.getString("message");
        Assert.assertEquals(errorMessage, "Error! This phone number doesn't exist in our DB");
    }
}
