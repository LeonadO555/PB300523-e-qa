package integration;

import integration.contact.ContactApi;
import integration.schemas.ContactDto;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactApiTest { // экстендится не надо так как драйвер не нужен, работаем под капотом
    UserApi userApi;
    ContactApi contactApi;

    private void checkContactData(int contactId, ContactDto contactData) {
        // чтобы сделать апдейт метод записываем в переменную, если есть респонс(есть не всегда)
        //после пост/ создания  всегда гет/проверка создания - вывел ли в интерфейс и потом ассерт - проверка данных
        JsonPath actualObjects = contactApi.getContact(200, contactId).jsonPath();// путь до обьекта до фигурных скобок
        LinkedHashMap<String, String> contactObjects = new LinkedHashMap<>(); // типизация обьекта, формируется из стрингов(ключ и вэлью)
        // вызываем рандомный мето и  гетером вытаскиваем
        contactObjects.put(actualObjects.getString("firstName"), contactData.getFirstName());
        contactObjects.put(actualObjects.getString("lastName"), contactData.getLastName());
        contactObjects.put(actualObjects.getString("description"), contactData.getDescription());
        for (Map.Entry<String, String> contactObject : contactObjects.entrySet()) {
            String actualResult = contactObject.getKey();
            String expectedResult = contactObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals" + expectedResult);
        }
    }

    @Test
    public void userCanWorkWithContactViaApi() {
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";

        // login as user and get Access token from Response Header
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);// метод записываем в переменную

        contactApi = new ContactApi(token); //
        JsonPath object = contactApi.createContact(201).jsonPath();
        int contactId = object.getInt("id"); // обращаемся к ключу id из обьекта чтобы вытащить его из обьека
        checkContactData(contactId, contactApi.rndDataForCreateContact());


        // put/update Contact
        contactApi.editContact(200, contactId);// put Access token to a class which need token for request
        checkContactData(contactId, contactApi.rndDataForEditContact(contactId));
        contactApi.deleteContact(200, contactId);
        JsonPath actualDeletedObject = contactApi.getContact(500, contactId).jsonPath();
        String errorMessage = actualDeletedObject.getString("message");
        Assert.assertEquals(errorMessage, "Error! This contact doesn't exist in our DB");

    }
}
