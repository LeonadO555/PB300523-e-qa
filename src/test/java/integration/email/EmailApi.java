package integration.email;

import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.EmailDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {

    public EmailApi(String token){
        super(token);
    }

    Response response;
    EmailDto emailDto;
    Faker faker = new Faker();
    String email = faker.internet().uuid();

    String editEmail = faker.internet().uuid();


    public EmailDto rndDataForCreateEmail(int contactId){
        emailDto = new EmailDto();
        emailDto.setEmail(email);
        emailDto.setContactId(contactId);
        return emailDto;
    }

    public EmailDto rndDataForEditEmail(int id,int contactId){
        emailDto = new EmailDto();
        emailDto.setId(id);
        emailDto.setEmail(editEmail);
        emailDto.setContactId(contactId);
        return emailDto;
    }

    public Response createEmail(int code,int contactId){
        String endpoint = "/api/email";
        Object body = rndDataForCreateEmail(contactId);
        response = postRequest(endpoint,code,body);
        return response;
    }

    public void editEmail(int id,int code, int contactId){
        String endpoint = "/api/email";
        Object body = rndDataForEditEmail(id,contactId);
        putRequest(endpoint,code,body);

    }

    public Response deleteEmail(int code, int id){
        String endpoint = "/api/email/{id}";
        response = deleteRequest(endpoint,code,id);
        return response;
    }

    public Response getAllEmail(int code, int contactId){
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint,code,"contactId", contactId); //"contactID"
        return response;
    }

    public Response getEmail(int code,int id){
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint,code,"id",id);
        return response;
    }
}
