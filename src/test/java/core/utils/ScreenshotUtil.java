package core.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import com.epam.healenium.SelfHealingDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void takeScreenshot(SelfHealingDriver driver, String testName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File dest = new File("target/screenshots/" + testName + ".png");

        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}