package integration.email;

import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.EmailDto;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {

    public EmailApi(String token) {
        super(token);
    }

    Response response;
    EmailDto dto;

    Faker faker = new Faker();
    String email = faker.internet().emailAddress();

    public EmailDto rndDataForCreateEmailAddress(int contactId) {
        dto = new EmailDto();
        dto.setEmail(email);
        dto.setContactID(contactId);
        return dto;
    }

    public Response createEmail(int code, int contactId) {
        String endpoint = "/api/email";
        Object body = rndDataForCreateEmailAddress(contactId);
        response = postRequest(endpoint, code, body);
        response.as(EmailDto.class);
        return response;
    }
}
