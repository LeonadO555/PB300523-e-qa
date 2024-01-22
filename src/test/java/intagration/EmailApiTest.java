package intagration;

import intagration.contact.ContactApi;
import intagration.email.EmailApi;
import intagration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
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
        emailApi = new EmailApi(token);
        emailApi.createEmail(201, contactId);

        JsonPath emailObject = emailApi.getAllEmails(200, contactId).jsonPath();
        int emailId = emailObject.getInt("[0].id");
        emailApi.editNewEmail(200, contactId, emailId);


        emailApi.deleteEmail(200,emailId);
        JsonPath actualDeletedObject = emailApi.getEmail(500, emailId).jsonPath();
        String errorMessage = actualDeletedObject.getString("message");
        Assert.assertEquals(errorMessage,"Error! This email doesn't exist in our DB");

    }

}
