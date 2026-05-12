package core.driver;

import com.epam.healenium.SelfHealingDriver;
import core.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static void initDriver() {

        String browser = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        boolean useHealenium = Boolean.parseBoolean(ConfigReader.get("useHealenium"));

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            WebDriver baseDriver = new ChromeDriver(options);

            if (useHealenium) {
                driver = SelfHealingDriver.create(baseDriver);
            } else {
                driver = baseDriver;
            }

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("timeout"))
                )
        );
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

