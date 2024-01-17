package integration;

import integration.contact.ContactApi;
import integration.phone.PhoneApi;
import integration.schemas.PhoneDto;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhoneApiTest {

    UserApi userApi;
    ContactApi contactApi;
    PhoneApi phoneApi;

    private void checkPhoneData(int phoneId, PhoneDto phoneData) {
        JsonPath actualObject = phoneApi.getPhone(200, phoneId).jsonPath();
        LinkedHashMap<String, String> phoneObjects = new LinkedHashMap<>();
        phoneObjects.put(actualObject.getString("countryCode"), phoneData.getCountryCode());
        phoneObjects.put(actualObject.getString("phoneNumber"), phoneData.getPhoneNumber());

        for (Map.Entry<String, String> phoneObject : phoneObjects.entrySet()) {
            String actualResult = phoneObject.getKey();
            String expectedResult = phoneObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }

    @Test
    public void userCanWorkWithPhoneViaApiTest() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);
        contactApi = new ContactApi(token);
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id");
        phoneApi = new PhoneApi(token);
        phoneApi.createNewPhone(201, contactId);
        JsonPath phoneObject = phoneApi.getAllPhones(200, contactId).jsonPath();
        int phoneId = phoneObject.getInt("id[0]");
        checkPhoneData(phoneId, phoneApi.rndForCreatedNewPhone(contactId));


        phoneApi.editNewPhone(201, contactId, phoneId);
        checkPhoneData(phoneId,phoneApi.rndForEditNewPhone(phoneId,contactId));

        phoneApi.deletePhone(200, phoneId);

        JsonPath actualDeleteObject = phoneApi.getPhone(500, phoneId).jsonPath();
        String errorMessage = actualDeleteObject.getString("message");
        Assert.assertEquals(errorMessage, "Error! This phone number doesn't exist in our DB");


    }
    }

