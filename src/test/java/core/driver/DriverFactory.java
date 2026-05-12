package core.driver;

import com.epam.healenium.SelfHealingDriver;
import core.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser = ConfigReader.get("browser");

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.get("headless")
                );

        boolean useHealenium =
                Boolean.parseBoolean(
                        ConfigReader.get("useHealenium")
                );

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options =
                    new ChromeOptions();

            if (headless) {

                options.addArguments("--headless=new");
            }

            WebDriver baseDriver =
                    new ChromeDriver(options);

            if (useHealenium) {

                driver.set(
                        SelfHealingDriver.create(baseDriver)
                );

            } else {

                driver.set(baseDriver);
            }
        }

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}