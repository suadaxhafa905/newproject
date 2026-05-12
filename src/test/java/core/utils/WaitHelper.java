package core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver, int timeout) {
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeout)
        );
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
}