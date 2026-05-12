package core.db;

import core.assertions.SoftAssertHelper;

public class DbAssertions {

    private final SoftAssertHelper softAssert;

    public DbAssertions(SoftAssertHelper softAssert) {
        this.softAssert = softAssert;
    }

    public void assertDbValueEquals(
            String testCase,
            String step,
            String query,
            String expectedValue,
            String severity
    ) {
        String actualValue = QueryExecutor.getSingleValue(query);

        softAssert.assertEquals(
                testCase,
                step,
                expectedValue,
                actualValue,
                severity
        );
    }
}