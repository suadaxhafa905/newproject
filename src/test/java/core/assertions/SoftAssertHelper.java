package core.assertions;

import core.driver.DriverFactory;
import core.utils.ScreenshotUtil;
import core.utils.WordBugReportManager;
import org.testng.asserts.SoftAssert;

public class SoftAssertHelper {

    private final SoftAssert softAssert =
            new SoftAssert();

    public void assertEquals(
            String testCase,
            String step,
            String expected,
            String actual,
            String severity
    ) {

        if (!expected.equals(actual)) {

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(
                            DriverFactory.getDriver(),
                            testCase + "_" + step
                    );

            WordBugReportManager.logBug(
                    testCase,
                    step,
                    expected,
                    actual,
                    severity,
                    screenshotPath
            );
        }

        softAssert.assertEquals(
                actual,
                expected,
                "Bug found in step: " + step
        );
    }

    public void assertTrue(
            String testCase,
            String step,
            boolean condition,
            String expected,
            String actual,
            String severity
    ) {

        if (!condition) {

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(
                            DriverFactory.getDriver(),
                            testCase + "_" + step
                    );

            WordBugReportManager.logBug(
                    testCase,
                    step,
                    expected,
                    actual,
                    severity,
                    screenshotPath
            );
        }

        softAssert.assertTrue(
                condition,
                "Bug found in step: " + step
        );
    }

    public void assertAll() {

        softAssert.assertAll();
    }
}