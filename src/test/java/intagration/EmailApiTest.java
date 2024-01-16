package intagration;

import intagration.contact.ContactApi;
import intagration.email.EmailApi;
import intagration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class EmailApiTest {

    UserApi userApi;
    ContactApi contactApi;
    EmailApi emailApi;

    @Test
    public void userCanWorkWithEmailApiTest(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        // login as user and get Access token from Response Header
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);

        contactApi = new ContactApi(token); // put Access token to class which need token from requests
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt( "id");

        //create email
        emailApi.createEmail(201, contactId);
        emailApi.getAllEmails(200,contactId);

    }

}
