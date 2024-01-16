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
    String email = faker.internet().emailAddress();

    public EmailDto rndDataForCreateEmailAddress(int contactId){
        dto = new EmailDto();
        dto.setEmail(email);
        dto.setContactId(contactId);
        return dto;
    }

    public Response createEmail(int code, int contactId){
        String endpoint ="/api/email";
        Object body = rndDataForCreateEmailAddress(contactId);
        response = postRequest(endpoint,code,body);
        response.as(EmailDto.class);
        return response;
    }

    public Response getAllEmails(int code, int id){
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint,code,"id",id);
        return response;
    }
}
