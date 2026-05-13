package core.api;

import core.context.ScenarioContext;
import io.restassured.response.Response;

public class ResponseHelper {

    public static void saveFieldFromResponse(
            Response response,
            String jsonPath,
            String contextKey
    ) {

        String value =
                response.jsonPath()
                        .getString(jsonPath);

        ScenarioContext.set(
                contextKey,
                value
        );

        System.out.println(
                "Saved to ScenarioContext -> "
                        + contextKey
                        + ": "
                        + value
        );
    }
}