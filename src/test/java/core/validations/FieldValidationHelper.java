package core.validations;

import core.assertions.SoftAssertHelper;

public class FieldValidationHelper {

    private final SoftAssertHelper softAssert;

    public FieldValidationHelper(
            SoftAssertHelper softAssert
    ) {
        this.softAssert = softAssert;
    }

    public void validateContains(
            String testCase,
            String step,
            String actual,
            String expected,
            String severity
    ) {

        softAssert.assertTrue(
                testCase,
                step,
                actual.contains(expected),
                expected,
                actual,
                severity
        );
    }

    public void validateEquals(
            String testCase,
            String step,
            String actual,
            String expected,
            String severity
    ) {

        softAssert.assertEquals(
                testCase,
                step,
                expected,
                actual,
                severity
        );
    }

    public void validateNotEmpty(
            String testCase,
            String step,
            String actual,
            String severity
    ) {

        softAssert.assertTrue(
                testCase,
                step,
                actual != null && !actual.trim().isEmpty(),
                "Non-empty value",
                actual,
                severity
        );
    }
}