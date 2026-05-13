package core.api;

import core.config.ConfigReader;
import core.utils.LoggerHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final Logger logger =
            LoggerHelper.getLogger(ApiClient.class);

    public Response get(String endpoint) {

        logger.info("Sending GET request to endpoint: {}", endpoint);

        Response response =
                given()
                        .baseUri(ConfigReader.get("baseUrl"))
                        .header("Content-Type", "application/json")
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();

        logger.info(
                "GET Response Status Code: {}",
                response.getStatusCode()
        );

        logger.info(
                "GET Response Body: {}",
                response.getBody().asString()
        );

        return response;
    }

    public Response post(String endpoint, String body) {

        logger.info("Sending POST request to endpoint: {}", endpoint);

        logger.info("POST Request Body: {}", body);

        Response response =
                given()
                        .baseUri(ConfigReader.get("baseUrl"))
                        .header("Content-Type", "application/json")
                        .body(body)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract()
                        .response();

        logger.info(
                "POST Response Status Code: {}",
                response.getStatusCode()
        );

        logger.info(
                "POST Response Body: {}",
                response.getBody().asString()
        );

        return response;
    }

    public Response getWithToken(String endpoint) {

        logger.info(
                "Sending authenticated GET request to endpoint: {}",
                endpoint
        );

        Response response =
                given()
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

        logger.info(
                "Authenticated GET Response Status Code: {}",
                response.getStatusCode()
        );

        logger.info(
                "Authenticated GET Response Body: {}",
                response.getBody().asString()
        );

        return response;
    }
}