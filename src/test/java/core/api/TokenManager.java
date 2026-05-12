package core.api;

import core.config.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String token;

    public static String generateToken() {

        if (token == null) {

            String requestBody = """
                    {
                      "username": "%s",
                      "password": "%s"
                    }
                    """.formatted(
                    ConfigReader.get("username"),
                    ConfigReader.get("password")
            );

            Response response =
                    given()
                            .baseUri(ConfigReader.get("baseUrl"))
                            .header("Content-Type", "application/json")
                            .body(requestBody)
                            .when()
                            .post(ConfigReader.get("tokenEndpoint"))
                            .then()
                            .extract()
                            .response();

            token =
                    response.jsonPath()
                            .getString(
                                    ConfigReader.get("tokenJsonPath")
                            );
        }

        return token;
    }
}