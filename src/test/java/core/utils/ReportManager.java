package core.utils;
/*
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("target/ExtentReports/report.html");

            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Tester", "Suada");
            extent.setSystemInfo("Framework", "Selenium + Cucumber");
        }

        return extent;
    }

}

 */

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.epam.healenium.SelfHealingDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

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
    }

    // Fillon testin/scenarin
    public static void startTest(String scenarioName) {

        test = extent.createTest(scenarioName);
    }

    // PASS
    public static void logPass(String message) {

        test.pass(message);
    }

    // FAIL + screenshot
    public static void logFail(String message, String screenshotPath) {

        test.fail(message)
                .addScreenCaptureFromPath(screenshotPath);
    }

    // Screenshot
    public static String captureScreenshot(
            SelfHealingDriver driver,
            String screenshotName) {

        File src =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        String path =
                "reports/screenshots/" +
                        screenshotName.replace(" ", "_") +
                        ".png";

        try {

            FileUtils.copyFile(src, new File(path));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return path;
    }

    // Ruaj reportin
    public static void flushReport() {

        extent.flush();
    }
}