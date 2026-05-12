package core.utils;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        String safeName = testName.replaceAll("[^a-zA-Z0-9]", "_");

        WebDriver screenshotDriver;

        if (driver instanceof SelfHealingDriver) {
            screenshotDriver = ((SelfHealingDriver) driver).getDelegate();
        } else {
            screenshotDriver = driver;
        }

        File src =
                ((TakesScreenshot) screenshotDriver)
                        .getScreenshotAs(OutputType.FILE);

        String path =
                "reports/screenshots/" + safeName + ".png";

        File dest = new File(path);

        dest.getParentFile().mkdirs();

        try {
            FileHandler.copy(src, dest);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        return path;
    }
}