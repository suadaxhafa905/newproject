package core.driver;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static SelfHealingDriver driver;

    public static void initDriver() {
        WebDriver base = new ChromeDriver();
        driver = SelfHealingDriver.create(base);
    }

    public static SelfHealingDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}