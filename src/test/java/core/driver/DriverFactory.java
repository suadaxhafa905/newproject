package core.driver;

import com.epam.healenium.SelfHealingDriver;
import core.config.ConfigReader;
import core.utils.LoggerHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static final Logger logger =
            LoggerHelper.getLogger(DriverFactory.class);

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser =
                ConfigReader.get("browser");

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.get("headless")
                );

        boolean useHealenium =
                Boolean.parseBoolean(
                        ConfigReader.get("useHealenium")
                );

        logger.info("Initializing browser: {}", browser);

        logger.info("Headless mode: {}", headless);

        logger.info("Healenium enabled: {}", useHealenium);

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options =
                    new ChromeOptions();

            if (headless) {

                options.addArguments("--headless=new");
            }

            WebDriver baseDriver =
                    new ChromeDriver(options);

            if (useHealenium) {

                logger.info("Wrapping driver with Healenium");

                driver.set(
                        SelfHealingDriver.create(baseDriver)
                );

            } else {

                driver.set(baseDriver);
            }
        }

        getDriver().manage().window().maximize();

        logger.info("Browser initialized successfully");
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();

            logger.info("Driver closed successfully");
        }
    }
}