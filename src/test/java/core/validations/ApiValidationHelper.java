package core.validations;

import io.restassured.response.Response;
import org.testng.Assert;

public class ApiValidationHelper {

    public void validateStatusCode(
            Response response,
            int expectedStatus
    ) {

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatus,
                "Unexpected status code"
        );
    }

    public void validateResponseContains(
            Response response,
            String expectedText
    ) {

        Assert.assertTrue(
                response.asString().contains(expectedText),
                "Response does not contain expected text"
        );
    }
}