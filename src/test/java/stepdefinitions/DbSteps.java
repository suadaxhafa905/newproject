package stepdefinitions;

import core.assertions.SoftAssertHelper;
import core.context.ScenarioContext;
import core.db.DbAssertions;
import io.cucumber.java.en.Then;

public class DbSteps {

    SoftAssertHelper softAssert = new SoftAssertHelper();
    DbAssertions dbAssertions = new DbAssertions(softAssert);

    @Then("DB value for query {string} should be {string} for test case {string}")
    public void dbValueForQueryShouldBeForTestCase(
            String query,
            String expectedValue,
            String testCase
    ) {
        dbAssertions.assertDbValueEquals(
                testCase,
                "Validate DB value",
                query,
                expectedValue,
                "HIGH"
        );

        softAssert.assertAll();
    }

    @Then("Generated user should exist in database for test case {string}")
    public void generatedUserShouldExistInDatabaseForTestCase(
            String testCase
    ) {

        String username =
                ScenarioContext.get("username")
                        .toString();

        String query =
                "SELECT COUNT(*) FROM Users WHERE Username='" +
                        username + "'";

        dbAssertions.assertDbValueEquals(
                testCase,
                "Validate generated user exists in DB",
                query,
                "1",
                "HIGH"
        );

        softAssert.assertAll();
    }
}