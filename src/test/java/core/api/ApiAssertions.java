package core.api;

import core.assertions.SoftAssertHelper;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiAssertions {

    private final SoftAssertHelper softAssert;

    public ApiAssertions(SoftAssertHelper softAssert) {
        this.softAssert = softAssert;
    }

    public void assertStatusCode(
            String testCase,
            Response response,
            int expectedStatusCode
    ) {
        int actualStatusCode = response.getStatusCode();

        softAssert.assertTrue(
                testCase,
                "Validate API status code",
                actualStatusCode == expectedStatusCode,
                "Expected status code: " + expectedStatusCode,
                "Actual status code: " + actualStatusCode,
                "HIGH"
        );
    }


    public void assertResponseContains(
            String testCase,
            Response response,
            String expectedText
    ) {
        String actualBody = response.getBody().asString();

        softAssert.assertTrue(
                testCase,
                "Validate API response body",
                actualBody.contains(expectedText),
                "Response should contain: " + expectedText,
                actualBody,
                "HIGH"
        );
    }


    public void assertSchema(
            String schemaPath,
            Response response
    ) {
        response.then().assertThat()
                .body(
                        matchesJsonSchemaInClasspath(schemaPath)
                );
    }


}