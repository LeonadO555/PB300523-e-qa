package intagration.user;

import intagration.ApiBase;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public class UserApi extends ApiBase {
    Response response;

    public String login(String email, String password, int code){
        String endpoint = "/api/user/login";
        LinkedHashMap<String,String> body = new LinkedHashMap<>();
        body.put("email",email);
        body.put("password",password);
        response = postRequest(endpoint, code,body);
        return response.header("Access-Token");
    }
}