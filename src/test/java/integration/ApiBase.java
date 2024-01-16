package integration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
    final String BASE_URL = "http://phonebook.telran-edu.de:8080/";

    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token",API_KEY)
            .build();

    protected Response getRequest(String endpoint,int code){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response getRequestWhitParam(String endpoint,int code,String paramName,int id){
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
    protected Response postRequest(String endpoint,int code,Object body){
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response putRequest(String endpoint,int code,Object body){
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
    protected Response deleteRequest(String endpoint,int code,int id){
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
}
