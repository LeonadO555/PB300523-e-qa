package integration.contact;

import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.ContactDto;
import io.restassured.response.Response;

public class ContactApi extends ApiBase { // всегда экстендится от апи бэйз

    public ContactApi(String token){
        super(token); // нужен для доставки токено на нижние уровни , базовые классы.
    }
   //
    Response response;
    ContactDto dto;
    Faker faker = new Faker();
    String firstName = faker.internet().uuid();// радомиться не будет каждый раз при использовании сетера и гетера
    String lastName = faker.internet().uuid();
    String description = faker.internet().uuid();
    String editFirstName = faker.internet().uuid();
    String editLastName = faker.internet().uuid();
    String editDescription = faker.internet().uuid();

    // method request body rnd
    public ContactDto rndDataForCreateContact(){
        dto = new ContactDto();
        dto.setFirstName(firstName); // записывает в память если используем рандом, потом рандом еще раз отработает при использю геттера, поэтому создаем переменную
        dto.setLastName(lastName);
        dto.setDescription(description);
        return dto; // выводит обьект
    }

    // боди для пут rnd
    public   ContactDto rndDataForEditContact(int id){
        dto = new ContactDto();
        dto.setId(id);
        dto.setFirstName(editFirstName);
        dto.setLastName(editLastName);
        dto.setDescription(editDescription);
        return dto;

    }
    // post
    public Response createContact(int code){
        String endpoint = "/api/contact";
        Object body = rndDataForCreateContact();
        response = postRequest(endpoint, code, body);
        response.as(ContactDto.class); // сравнивает ответ кот пришел и схема, кот лежит
        return response;

    }
    // put
    public void editContact(int code, int id){
        String endpoint = "/api/contact";
        Object body = rndDataForEditContact(id);
        putRequest(endpoint, code, body);
    }

    // delete
    public   Response deleteContact(int code, int id){
        String endpoint = "/api/contact/{id}";
        response = deleteRequest(endpoint, code, id);
        return response;
    }
    // get contact
    public Response getContact(int code,int id){
        String endpoint = "/api/contact/{id}";
        response = getRequestWithParam(endpoint,code, "id", id);
        return response;
    }
}
