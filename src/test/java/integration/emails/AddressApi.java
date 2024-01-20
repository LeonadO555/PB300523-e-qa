package integration.emails;

import integration.ApiBase;
import integration.schemas.AddressDto;
import integration.schemas.PhoneDto;
import io.restassured.response.Response;

public class AddressApi extends ApiBase {

    public AddressApi(String token) {
        super(token);
    }

    AddressDto addressDto;
    Response response;
    String city = "Dresden";
    String country = "Germany";
    String street = "DresdnerStraße 8";
    String zip = "01936";

    String updatedCity = "Berlin";
    String updatedCountry = "Germany";
    String updatedStreet = "BerlinerStraße 8";
    String updatedZip = "01010";

    public AddressDto rndForCreatedNewPhone(int contactId) {
        addressDto = new AddressDto();
        addressDto.setCity(city);
        addressDto.setCountry(country);
        addressDto.setStreet(street);
        addressDto.setZip(zip);
        addressDto.setContactId(contactId);
        return addressDto;
    }
    public AddressDto rnbForUpdateAddress(int addressId,int contactId){
        addressDto = new AddressDto();
        addressDto.setId(addressId);
        addressDto.setCity(updatedCity);
        addressDto.setCountry(updatedCountry);
        addressDto.setStreet(updatedStreet);
        addressDto.setZip(updatedZip);
        addressDto.setContactId(contactId);
        return addressDto;
    }
    public void createNewAddress(int code,int contactId){
        String endpoint = "/api/address";
        Object body = rndForCreatedNewPhone(contactId);
        postRequest(endpoint,code,body);
    }
    public void updateAddress(int code,int addressId,int contactId){
        String endpoint = "/api/address";
        Object body = rnbForUpdateAddress(addressId,contactId);
        putRequest(endpoint,code,body);
    }
    public void deleteAddress(int code,int addressId){
        String endPoint="/api/address/{id}";
        deleteRequest(endPoint,code,addressId);
    }
    public Response getAddress(int code,int addressId){
        String endpoint = "/api/address/{id}";
        response = getRequestWhitParam(endpoint,code,"id",addressId);
        return response;
    }
    public Response getAllAddress(int code,int contactId){
        String endpoint = "/api/address/{contactId}/all";
        response = getRequestWhitParam(endpoint,code,"contactId",contactId);
        return  response;
    }
}
