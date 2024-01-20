package integration;

import integration.contact.ContactApi;
import integration.emails.AddressApi;
import integration.schemas.AddressDto;
import integration.schemas.PhoneDto;
import integration.user.UserApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class AddressApiTest {
    UserApi userApi;
    ContactApi contactApi;
    AddressApi addressApi;
    private void checkAddressData(int addressId, AddressDto addressData) {
        JsonPath actualObject = addressApi.getAddress(200, addressId).jsonPath();
        LinkedHashMap<String, String> addressObjects = new LinkedHashMap<>();
        addressObjects.put(actualObject.getString("city"), addressData.getCity());
        addressObjects.put(actualObject.getString("country"), addressData.getCountry());
        addressObjects.put(actualObject.getString("street"), addressData.getStreet());
        addressObjects.put(actualObject.getString("zip"), addressData.getZip());

        for (Map.Entry<String, String> addressObject : addressObjects.entrySet()) {
            String actualResult = addressObject.getKey();
            String expectedResult = addressObject.getValue();
            Assert.assertEquals(actualResult, expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }
    @Test
    public void userCanWorkWithAddressViaApiTest(){
        String email = "newtest@gmail.com";
        String password = "newtest@gmail.com";
        userApi = new UserApi();
        String token = userApi.login(email,password,200);
        contactApi = new ContactApi(token);
        JsonPath contactObject = contactApi.createContact(201).jsonPath();
        int contactId = contactObject.getInt("id");
        addressApi = new AddressApi(token);
        addressApi.createNewAddress(201,contactId);
        JsonPath addressObject = addressApi.getAllAddress(200,contactId).jsonPath();
        int addressId = addressObject.getInt("[0].id");
        checkAddressData(addressId,addressApi.rndForCreatedNewPhone(contactId));

        addressApi.updateAddress(200,addressId,contactId);
        checkAddressData(addressId,addressApi.rnbForUpdateAddress(addressId,contactId));

        addressApi.deleteAddress(200,addressId);
        JsonPath actualDeleteAddressObject = addressApi.getAddress(500,addressId).jsonPath();
        String errorMassage = actualDeleteAddressObject.getString("message");
        Assert.assertEquals(errorMassage,"Error! This address doesn't exist in our DB");
    }
}
