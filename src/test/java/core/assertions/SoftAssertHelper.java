package core.assertions;

import core.config.ConfigReader;
import core.context.ExecutionInfo;
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
        assertEquals(
                testCase,
                step,
                expected,
                actual,
                "N/A",
                "N/A",
                severity,
                "HIGH",
                "UI Validation"
        );
    }

    public void assertEquals(
            String testCase,
            String step,
            String expected,
            String actual,
            String module,
            String page,
            String severity,
            String priority,
            String bugType
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
                    module,
                    page,
                    severity,
                    priority,
                    bugType,
                    getCurrentUrl(),
                    ConfigReader.get("browser"),
                    ExecutionInfo.getBrowserVersion(),
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
        assertTrue(
                testCase,
                step,
                condition,
                expected,
                actual,
                "N/A",
                "N/A",
                severity,
                "HIGH",
                "UI Validation"
        );
    }

    public void assertTrue(
            String testCase,
            String step,
            boolean condition,
            String expected,
            String actual,
            String severity,
            String priority,
            String bugType
    ) {
        assertTrue(
                testCase,
                step,
                condition,
                expected,
                actual,
                "N/A",
                "N/A",
                severity,
                priority,
                bugType
        );
    }

    public void assertTrue(
            String testCase,
            String step,
            boolean condition,
            String expected,
            String actual,
            String module,
            String page,
            String severity,
            String priority,
            String bugType
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
                    module,
                    page,
                    severity,
                    priority,
                    bugType,
                    getCurrentUrl(),
                    ConfigReader.get("browser"),
                    ExecutionInfo.getBrowserVersion(),
                    screenshotPath
            );
        }

        softAssert.assertTrue(
                condition,
                "Bug found in step: " + step
        );
    }

    private String getCurrentUrl() {

        try {
            if (DriverFactory.getDriver() != null) {
                return DriverFactory.getDriver().getCurrentUrl();
            }
        } catch (Exception e) {
            return "N/A";
        }

        return "N/A";
    }

    public void assertAll() {

        softAssert.assertAll();
    }
}