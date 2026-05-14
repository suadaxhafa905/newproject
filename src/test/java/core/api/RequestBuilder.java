package core.api;


import com.github.javafaker.Faker;
import core.context.ScenarioContext;
import core.testdata.TestDataFactory;

public class RequestBuilder {

    private static final Faker faker = new Faker();

    public static String createUserBody() {

        String username = TestDataFactory.randomUsername();
        String email = username + "@test.com";
        String password = TestDataFactory.randomPassword();

        ScenarioContext.set("username", username);
        ScenarioContext.set("email", email);

        return """
                {
                  "username": "%s",
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(username, email, password);
    }
}
/*
import com.github.javafaker.Faker;

public class RequestBuilder {

    private static final Faker faker = new Faker();

    public static String createUserBody() {

        String username = "user_" + faker.number().digits(5);
        String email = username + "@test.com";
        String password = "Test123!";

        return """
                {
                  "username": "%s",
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(username, email, password);
    }
}

 */