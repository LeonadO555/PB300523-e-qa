package integration;

import integration.contact.ContactApi;
import integration.phone.PhoneApi;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
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
        phoneApi = new PhoneApi();
       phoneApi.createNewPhone(200,contactId)

    }
}
