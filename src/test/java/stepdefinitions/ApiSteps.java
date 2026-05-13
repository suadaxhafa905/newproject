package stepdefinitions;

import core.api.ApiAssertions;
import core.api.ApiClient;
import core.api.RequestBuilder;
import core.assertions.SoftAssertHelper;
import core.utils.JsonReader;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import core.context.ScenarioContext;
import core.api.ResponseHelper;

public class ApiSteps {

    private ApiClient apiClient;
    private ApiAssertions apiAssertions;
    private SoftAssertHelper softAssert;
    private Response response;

    @Given("API client is initialized")
    public void apiClientIsInitialized() {

        softAssert = new SoftAssertHelper();
        apiClient = new ApiClient();
        apiAssertions = new ApiAssertions(softAssert);
    }

    @When("I send GET request to {string}")
    public void iSendGetRequestTo(String endpoint) {

        response = apiClient.get(endpoint);
    }

    @Then("API status code should be {int} for test case {string}")
    public void apiStatusCodeShouldBeForTestCase(
            int expectedStatusCode,
            String testCase
    ) {
        apiAssertions.assertStatusCode(
                testCase,
                response,
                expectedStatusCode
        );

        softAssert.assertAll();
    }


    @When("I send POST request to {string} with body from {string}")
    public void iSendPostRequestToWithBodyFrom(
            String endpoint,
            String jsonPath
    ) {
        String body = JsonReader.readJsonFile(jsonPath);

        response = apiClient.post(endpoint, body);
    }


    @Then("API response should contain {string} for test case {string}")
    public void apiResponseShouldContainForTestCase(
            String expectedText,
            String testCase
    ) {
        apiAssertions.assertResponseContains(
                testCase,
                response,
                expectedText
        );

        softAssert.assertAll();
    }


    @When("I send authenticated GET request to {string}")
    public void iSendAuthenticatedGetRequestTo(
            String endpoint
    ) {
        response =
                apiClient.getWithToken(endpoint);
    }


    @When("I send POST request to {string} with generated user body")
    public void iSendPostRequestToWithGeneratedUserBody(String endpoint) {

        String body = RequestBuilder.createUserBody();

        response = apiClient.post(endpoint, body);

        System.out.println("REQUEST BODY: " + body);
        System.out.println("STATUS CODE: " + response.getStatusCode());
        System.out.println("RESPONSE BODY: " + response.getBody().asString());

        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {

       //     String userId = response.jsonPath().getString("id");

          //  ScenarioContext.set("userId", userId);

        //    System.out.println("Stored userId in ScenarioContext: " + userId);

            ResponseHelper.saveFieldFromResponse(
                    response,
                    "id",
                    "userId"
            );
        }
    }



    @Then("API response should match schema {string}")
    public void apiResponseShouldMatchSchema(
            String schemaPath
    ) {
        apiAssertions.assertSchema(
                schemaPath,
                response
        );
    }
}