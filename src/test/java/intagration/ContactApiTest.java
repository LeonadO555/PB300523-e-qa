package intagration;

import intagration.contact.ContactApi;
import intagration.shemas.ContactDto;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactApiTest {
    ContactApi contactApi;

    private void checkContactData(int contactId, ContactDto contactData){

        JsonPath actualObjects = contactApi.getContact(200,contactId).jsonPath();
        LinkedHashMap<String, String > contactObjects = new LinkedHashMap<>();
        contactObjects.put(actualObjects.getString("firstName"),contactData.getFirstName());
        contactObjects.put(actualObjects.getString("lastName"),contactData.getLastName());
        contactObjects.put(actualObjects.getString("description"),contactData.getDescription());
        for (Map.Entry<String, String> contactObject:contactObjects.entrySet()){
            String actualResult = contactObject.getKey();
            String expectedResult = contactObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult +" is not equals " + expectedResult);
        }
    }
    @Test
    //create contact
    public void userCanWorkWithContactViaApiTest(){
        contactApi = new ContactApi();
        JsonPath object = contactApi.createContact(200).jsonPath();
        int contactId = object.getInt("id");
        checkContactData(contactId, contactApi.rndDataForCreateContact());
        //update

        contactApi.editContact(200, contactId);
        checkContactData(contactId, contactApi.rndDataForEditContact(contactId));

        //delete Contact
        contactApi.deleteContact(200,contactId);
        JsonPath actualDeletedObject = contactApi.getContact(500,contactId).jsonPath();
        String errorMessage = actualDeletedObject.getString("message");
        Assert.assertEquals(errorMessage,"Error! this contact doesn't exist in our DB");
    }
}
