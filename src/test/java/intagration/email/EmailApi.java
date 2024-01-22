package intagration.email;

import com.github.javafaker.Faker;
import intagration.ApiBase;
import intagration.schemas.ContactDto;
import intagration.schemas.EmailDto;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {

    public EmailApi(String token){
        super(token);
    }

    Response response;
    EmailDto dto;

    Faker faker = new Faker();

    String editEmail = faker.internet().emailAddress();
    String email = faker.internet().emailAddress();
    //String editEmail = "alex@gmail.com";
    //String email = "alex123@gmail.com";

    public EmailDto rndDataForCreateEmailAddress(int contactId){
        dto = new EmailDto();
        dto.setEmail(email);
        dto.setContactId(contactId);
        return dto;
    }

    public EmailDto rndDataForEditEmailAddress(int id,int contactId){
        dto = new EmailDto();
        dto.setId(id);
        dto.setEmail(editEmail);
        dto.setContactId(contactId);
        return dto;
    }

    public void createEmail(int code, int contactId){
        String endpoint ="/api/email";
        Object body = rndDataForCreateEmailAddress(contactId);
        response = postRequest(endpoint,code,body);
    }

    public void editNewEmail(int code, int contactId, int id) {
        String endpoint = "/api/email";
        Object body = rndDataForEditEmailAddress(id,contactId);
        putRequest(endpoint, code, body);
    }

    public Response getAllEmails(int code, int contactId){
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint,code,"contactId",contactId);
        return response;
    }

    public Response getEmail(int code, int id) {
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint, code, "id", id);
        return response;
    }

    public Response deleteEmail(int code, int id) {
        String endpoint = "/api/email/{id}";
        response = deleteRequest(endpoint, code, id);
        return response;
    }
}
