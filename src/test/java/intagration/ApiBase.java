package intagration;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {

    private final Config config = new Config();

    final String BASE_URI = config.getProjectUrl();

    final String API_KEY="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6Im5ld3Rlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTcwNTA4NzYwM30.bJCAwSbrOcMI5serP8oqEDwOaGFj9ThLtqodGQfw1BHahjnNtsD7NRRLPdP7xu6j2gMC9hnz1evKNXnuVQwXLQ";

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token",API_KEY)
            .build();

    protected Response getRequest(String endpoint, int code){
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
}
