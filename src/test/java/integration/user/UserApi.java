package integration.user;

import integration.ApiBase;
import integration.schemas.UserDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public class UserApi extends ApiBase {

    UserDto userDto;
    Response response;

    @Step("Login via api: {email}, {password}")
    public String login(String email, String password, int code) {
        String endpoint = "/api/user/login";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        response = postRequest(endpoint, code, body);
        return response.header("Access-Token");
    }

    @Step("Create new user via api: email, password")
    public String newUserRegistration(String email, String password, int code) {
        String endpoint = "/api/user";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        return postRequest(endpoint, code, body).asString();
    }

    @Step("Activation via api:{token}")
    public void activation( String token ,int code) {
        String endpoint = "/api/user/activation/{token}";
        getRequestWithParamString(endpoint, code,"token",token);
    }
}

