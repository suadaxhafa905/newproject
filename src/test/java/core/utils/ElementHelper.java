package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.ElementClickInterceptedException;
public class ElementHelper {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public ElementHelper(WebDriver driver, int timeout) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, timeout);
    }

    public void click(By locator) {

        try {

            WebElement element =
                    waitHelper.waitForClickability(locator);

            element.click();

        } catch (ElementClickInterceptedException e) {

            WebElement element =
                    waitHelper.waitForClickability(locator);

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView(true);",
                            element
                    );

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            element
                    );
        }
    }

    public void type(By locator, String text) {
        WebElement element = waitHelper.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitHelper.waitForVisibility(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitHelper.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(
                waitHelper.waitForVisibility(locator)
        );
        select.selectByVisibleText(text);
    }

    public void jsClick(By locator) {
        WebElement element = waitHelper.waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    public void scrollIntoView(By locator) {
        WebElement element = waitHelper.waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }
}