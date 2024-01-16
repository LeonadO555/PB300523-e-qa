package intagration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {

    final  String BASE_URI = "http://phonebook.telran-edu.de:8080/";

    final String API_REY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6Im5ld3Rlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTcwNDkxNDAxNn0.JN-uZJD6kUbaNSl6uzQMqwfZ2p0mgdwW3POEn0gWC43jcju62tquz06sB5PAvouJJ_uCrg1QeqALjNUttVVfdQ";
    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access Token",API_REY)
            .build();

    protected Response getRequest(String endpoint, int code) {
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


    protected Response getRequestWithParam(String endpoint, int code,String paramName, int id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParams(paramName,id)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response postRequest(String endpoint, int code, Object body) {
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

    protected Response putRequest(String endpoint, int code, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .put(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response deleteRequest(String endpoint, int code, int id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParams("id",id)
                .log().all()
                .delete(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

}

