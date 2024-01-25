package integration.user;

import integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

// конструктора не будет и в таком случае все эндпоинты которыев этом классе будут описаны, они не будут нуждаться в токене
// по умолчанию без токена
public class UserApi extends ApiBase {
    Response response;

    @Step("Login via api: {email}, {password}")
    // login method
    public String login(String email, String password, int code){
        String endpoint = "/api/user/login";
        LinkedHashMap<String,String> body = new LinkedHashMap<>();// формируем сам запрос боди и передаем в респонс
        body.put("email", email);
        body.put("password", password);
        response = postRequest(endpoint, code, body);
        return response.header("Access-Token"); // из респонса вытаскиваем хэдэр
    }
}
