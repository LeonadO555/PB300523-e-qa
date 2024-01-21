package integration;

import integration.contact.ContactApi;
import integration.email.EmailApi;
import integration.user.UserApi;
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
        String token = userApi.login(email, password, 200);// метод записываем в переменную

        contactApi = new ContactApi(token); //
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id"); // обращаемся к ключу id из обь

        // crate email
        emailApi = new EmailApi(token);
        emailApi.createEmail(201, contactId);
        //emailApi.getAllEmails(200, contactId);

        JsonPath emailObject = emailApi.getAllEmails(200, contactId).jsonPath();
        int emailID = emailObject.getInt("[0].id"); // 0 - первый обьект у массива нашли. точка - у этого обьекта обращаемся к id

        // edit email
        emailApi.editNewEmail(200, contactId, emailID);

    }
}
