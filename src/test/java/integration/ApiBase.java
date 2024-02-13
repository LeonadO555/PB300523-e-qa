package integration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ApiBase {

    private final config.Config config = new config.Config();

    final String BASE_URI = config.getProjectUrl();
    private final RequestSpecification spec; // в случае если передаем токен или не передаем в апибэйс будет записываться с переменной и отрабатывает соотв. конструктор


    // create constructor -- name of class
    public ApiBase(){ // without token
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build(); // базовая спецификация запросов: гет, пут...

    }
    public ApiBase(String token){ // with token
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Access-Token", token)
                .build();
    }

//
//    // билдтся базовая спецификация для всех, кот хранит авторизацию
//    RequestSpecification spec = new  RequestSpecBuilder()
//            .setBaseUri(BASE_URI)
//            .setContentType(ContentType.JSON)
//            .addHeader("Access-Token", API_KEY)
//            .build(); // базовая спецификация запросов: гет, пут...

    protected Response getRequest(String endpoint, int code){
        // говорим классу Assured применяй
        Response response = RestAssured.given()
                .spec(spec)// применяй спецификацию,
                        // когда
                .when()
                        // логи нужны для того., чтобы понимать, что токен не испортилсяю потому что апи кей генерируется на какое-то время
                .log().all()// гет метод
                .get(endpoint)
                .then().log().all()
                .extract().response();
                // получаем ответ
        response.then().assertThat().statusCode(code);
        return response;

    }
    protected Response getRequestWithParam(String endpoint, int code, String paramName, int id){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName,id)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;

    }
    protected Response postRequest(String endpoint, int code, Object body){
        Response response = RestAssured.given()
                .spec(spec) //билдится специф, логин по ключу
                .body(body) // передаем сюдя обьект любой какой хотим
                .when() // когда выводит логи все
                .log().all()
                // принимает на себя эндпоинт
                .post(endpoint)
                .then().log().all()
                .extract().response(); // получает ответ
        response.then().assertThat().statusCode(code); // достаем код (201...)из переменной и сверяем его с тем кодом, кот передаем
        return response;
    }

    protected Response putRequest(String endpoint, int code, Object body){
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .put(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response deleteRequest(String endpoint, int code, int id){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id",id)
                .log().all()
                .delete(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response getRequestWithParamString(String endpoint, int code, String paramName, String paramValue){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName,paramValue)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;

    }


}