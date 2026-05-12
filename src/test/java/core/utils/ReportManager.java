package core.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

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
        test = extent.createTest(scenarioName);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    public static void logFailWithScreenshot(String message, String screenshotPath) {
        test.fail(message);
        test.addScreenCaptureFromPath(screenshotPath);
    }

    public static void flushReport() {
        extent.flush();
    }
}