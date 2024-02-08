package integration.user;

import integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public class UserApi extends ApiBase {
    Response response;

    @Step("Login via api: {email}, {password}")

    public String login(String email,String password,int code){
        String endpoint = "/api/user/login";
        LinkedHashMap<String,String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        response = postRequest(endpoint,code,body);
        return response.header("Access-Token");
    }
}
