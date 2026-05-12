package core.api;

import core.config.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public Response get(String endpoint) {

        return given()
                .baseUri(ConfigReader.get("baseUrl"))
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response post(String endpoint, String body) {

        return given()
                .baseUri(ConfigReader.get("baseUrl"))
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response getWithToken(String endpoint) {

        return given()
                .baseUri(ConfigReader.get("baseUrl"))
                .header(
                        "Authorization",
                        "Bearer " + TokenManager.generateToken()
                )
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

}