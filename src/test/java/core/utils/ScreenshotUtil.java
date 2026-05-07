package core.utils;
/*
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

 */

/*
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {

        String safeName = testName.replaceAll(" ", "_");

        File src =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        File dest =
                new File("target/screenshots/" + safeName + ".png");

        // krijon folder automatikisht
        dest.getParentFile().mkdirs();

        try {

            FileHandler.copy(src, dest);

            System.out.println(
                    "Screenshot saved: " + dest.getAbsolutePath()
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

 */


import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {

        String safeName = testName.replaceAll(" ", "_");

        // Merr driver-in real nga Healenium
        WebDriver delegate =
                ((SelfHealingDriver) driver).getDelegate();

        File src =
                ((TakesScreenshot) delegate)
                        .getScreenshotAs(OutputType.FILE);

        File dest =
                new File("target/screenshots/" + safeName + ".png");

        // krijon folderin automatikisht
        dest.getParentFile().mkdirs();

        try {

            FileHandler.copy(src, dest);

            System.out.println(
                    "Screenshot saved successfully"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}