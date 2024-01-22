package integration.email;

import integration.ApiBase;
import integration.schemas.EmailDto;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {
    public EmailApi(String token) {
        super(token);
    }

    Response response;
    EmailDto emailDto;
    String email = "natalie@gmail.com";
    String editEmail = "natalieK@gmail.com";

    public EmailDto rnDataForCreatedEmail(int contactId) {
        emailDto = new EmailDto();
        emailDto.setEmail(email);
        emailDto.setContactId(contactId);
        return emailDto;
    }

    public EmailDto rnDataForEditEmail(int id, int contactId) {
        emailDto = new EmailDto();
        emailDto.setId(id);
        emailDto.setEmail(editEmail);
        emailDto.setContactId(contactId);
        return emailDto;
    }

    public void createEmail(int code, int contactId) {
        String endpoint = "/api/email";
        Object body = rnDataForCreatedEmail(contactId);
        response = postRequest(endpoint, code, body);
    }

    public void editEmail(int code, int id, int contactId) {
        String endpoint = "/api/email";
        Object body = rnDataForEditEmail(id, contactId);
        putRequest(endpoint, code, body);
    }

    public Response deleteEmail(int code, int id) {
        String endpoint = "/api/email/{id}";
        response = deleteRequest(endpoint, code, id);
        return response;
    }

    public Response getAllEmail(int code, int contactId) {
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "contactId", contactId);
        return response;
    }

    public Response getEmail(int code, int id) {
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint, code, "id", id);
        return response;
    }
}
