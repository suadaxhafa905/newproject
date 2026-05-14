package core.utils;

import core.config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;

        int timeout =
                Integer.parseInt(ConfigReader.get("timeout"));

        this.wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public WebElement waitForClickability(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public boolean waitForInvisibility(By locator) {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public boolean waitForText(By locator, String text) {
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text)
        );
    }

    public void waitForUrlContains(String text) {
        wait.until(
                ExpectedConditions.urlContains(text)
        );
    }
}