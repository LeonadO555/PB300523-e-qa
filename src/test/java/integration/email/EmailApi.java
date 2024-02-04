package integration.email;

import integration.ApiBase;
import integration.schemas.EmailDto;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {
    public EmailApi(String token) {
        super(token);
    }

    EmailDto emailDto;
    Response response;
    String email = "rashevchenkoo@gmail.com";
    String updatedEmail = "hadenko2004@gamil.com";

    public EmailDto rndForCreatedNewEmail(int contactId) {
        emailDto = new EmailDto();
        emailDto.setEmail(email);
        emailDto.setContactId(contactId);
        return emailDto;
    }
    public EmailDto rndForUpdatedEmail(int emailId, int contactId){
        emailDto = new EmailDto();
        emailDto.setId(emailId);
        emailDto.setEmail(updatedEmail);
        emailDto.setContactId(contactId);
        return emailDto;
    }

    public void createNewEmail(int code,int contactId){
        String endpoint = "/api/email";
        Object body = rndForCreatedNewEmail(contactId);
        postRequest(endpoint,code,body);
    }
    public void updateEmail(int code,int emailId,int contactId){
        String endpoint ="/api/email";
        Object body = rndForUpdatedEmail(emailId,contactId);
        putRequest(endpoint,code,body);
    }
    public void deleteEmail(int code,int addressid){
        String endpoint = "/api/email/{id}";
        deleteRequest(endpoint,code,addressid);
    }
    public Response getAllEmail(int code,int contactId){
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWhitParam(endpoint,code,"contactId",contactId);
        return response;
    }
    public Response getEmail (int code,int emailId){
        String endpoint = "/api/email/{id}";
        response = getRequestWhitParam(endpoint,code,"id",emailId);
        return  response;
    }
}