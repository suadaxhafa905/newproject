package core.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static final ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    static {
        ExtentSparkReporter spark =
                new ExtentSparkReporter("reports/TestReport.html");

        spark.config().setReportName("Automation Report");
        spark.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Suada");
        extent.setSystemInfo("Framework", "Selenium + Cucumber + TestNG");
    }

    public static void startTest(String scenarioName) {

        ExtentTest extentTest =
                extent.createTest(scenarioName);

        test.set(extentTest);
    }

    public static void logPass(String message) {

        if (test.get() != null) {
            test.get().pass(message);
        }
    }

    public static void logFail(String message) {

        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    public static void logFailWithScreenshot(
            String message,
            String screenshotPath
    ) {

        if (test.get() != null) {
            test.get().fail(message);
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    public static void flushReport() {

        extent.flush();

        test.remove();
    }

    public static void logPassWithScreenshot(
            String message,
            String screenshotPath
    ) {

        if (test.get() != null) {

            test.get().pass(message);

            test.get().addScreenCaptureFromPath(
                    screenshotPath
            );
        }
    }
}