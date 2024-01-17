package integration.phone;

import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.PhoneDto;
import io.restassured.response.Response;


public class PhoneApi extends ApiBase {

    public PhoneApi(String token){
        super(token);
    }
    PhoneDto phoneDto;
    Response response;


    String countryCode = "+49";
    String phoneNumber = "3473739238";

    String editCountryCode = "+48";
    String editPhoneNumber = "3473733339238";

    public PhoneDto rndForCreatedNewPhone(int contactId){
        phoneDto = new PhoneDto();
        phoneDto.setCountryCode(countryCode);
        phoneDto.setPhoneNumber(phoneNumber);
        phoneDto.setContactId(contactId);
        return phoneDto;
    }
    public PhoneDto rndForEditNewPhone(int id,int contactId){
        phoneDto = new PhoneDto();
        phoneDto.setId(id);
        phoneDto.setCountryCode(editCountryCode);
        phoneDto.setPhoneNumber(editPhoneNumber);
        phoneDto.setContactId(contactId);
        return phoneDto;
    }
    public void createNewPhone(int code,int contactId){
        String endPoint = "/api/phone";
        Object body = rndForCreatedNewPhone(contactId);
        response = postRequest(endPoint,code,body);

    }
    public void editNewPhone(int code,int contactId,int id){
        String endPoint = "/api/phone";
        Object body = rndForEditNewPhone(id,contactId);
        response = postRequest(endPoint,code,body);
        postRequest(endPoint,code,body);
    }

    public Response deletePhone(int code,int id){
        String endPoint = "/api/phone/{id}";
        response = deleteRequest(endPoint,code,id);
        return  response;
    }
    public Response getAllPhones(int code,int contactId){
        String endpoint = "/api/phone/{contactId}/all";
        response = getRequestWhitParam(endpoint,code,"contactId",contactId);
        return response;
    }
    public Response getPhone(int code,int id){
        String endpoint = "/api/phone/{id}";
        response = getRequestWhitParam(endpoint,code,"id",id);
        return response;
    }

}
