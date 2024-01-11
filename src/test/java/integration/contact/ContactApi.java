package integration.contact;

import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.ContactDto;
import io.restassured.response.Response;

public class ContactApi extends ApiBase {
    Response response;
    ContactDto dto;
    Faker faker = new Faker();
    String firstName = faker.internet().uuid();
    String lastName = faker.internet().uuid();
    String description = faker.lorem().sentence();
    String editFirstName = faker.internet().uuid();
    String editLastName = faker.internet().uuid();
    String editDescription = faker.internet().uuid();

    public ContactDto rndDataForCreatedContact() {
        dto = new ContactDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setDescription(description);
        return dto;
    }

    public ContactDto rndDataForEditContact(int id){
        dto = new ContactDto();
        dto.setId(id);
        dto.setFirstName(editFirstName);
        dto.setLastName(editLastName);
        dto.setDescription(editDescription);
        return dto;
    }

    public Response createContact(int code) {
        String endpoint = "/api/contact";
        Object body = rndDataForCreatedContact();
        response = postRequest(endpoint, code, body);
        response.as(ContactDto.class);
        return response;
    }
    public void editContact(int code, int id){
        String endpoint = "/api/contact";
        Object body = rndDataForEditContact(id);
        putRequest(endpoint,code,body);
    }
    public Response deleteContact(int code, int id){
        String endpoint = "/api/contact/{id}";
        response = deleteRequest(endpoint,code,id);
        return response;
    }
    public Response getContact(int code, int id){
        String endpoint = "/api/contact/{id}";
        response = getRequestWithParam(endpoint,code, "id", id);
        return response;
    }
}
