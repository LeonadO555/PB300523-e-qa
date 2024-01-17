package integration;

import integration.contact.ContactApi;
import integration.phone.PhoneApi;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneApiTest {

    UserApi userApi;
    ContactApi contactApi;
    PhoneApi phoneApi;

    @Test
    public void userCanWorkWithPhoneViaApiTest() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        userApi = new UserApi();
        String token = userApi.login(email,password,200);
        contactApi = new ContactApi(token);
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id");
        phoneApi = new PhoneApi(token);
        phoneApi.createNewPhone(201,contactId);
        JsonPath phoneObject=phoneApi.getAllPhones(200,contactId).jsonPath();
        int phoneId = phoneObject.getInt("id[0]");

        phoneApi.editNewPhone(201,contactId,phoneId);

        phoneApi.deletePhone(200,phoneId);

        JsonPath actualDeleteObject = phoneApi.getPhone(500,phoneId).jsonPath();
        String errorMessage = actualDeleteObject.getString("message");
        Assert.assertEquals(errorMessage,"Error! This phone number doesn't exist in our DB");






    }
}
