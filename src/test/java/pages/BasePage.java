package pages;

import core.config.ConfigReader;
import core.utils.ElementHelper;
import core.utils.RetryHelper;
import core.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected ElementHelper elementHelper;
    protected WaitHelper waitHelper;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        int timeout =
                Integer.parseInt(ConfigReader.get("timeout"));

        this.elementHelper =
                new ElementHelper(driver, timeout);

        this.waitHelper =
                new WaitHelper(driver);
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected void click(By locator) {
        safeClick(locator);
    }

    protected void type(By locator, String text) {
        safeType(locator, text);
    }

    protected void safeClick(By locator) {
        RetryHelper.retryAction(
                () -> elementHelper.click(locator),
                3
        );
    }

    protected void safeType(By locator, String text) {
        RetryHelper.retryAction(
                () -> elementHelper.type(locator, text),
                3
        );
    }

    protected String getText(By locator) {
        return elementHelper.getText(locator);
    }

    protected boolean isDisplayed(By locator) {
        return elementHelper.isDisplayed(locator);
    }

    protected void jsClick(By locator) {
        elementHelper.jsClick(locator);
    }

    protected void scrollIntoView(By locator) {
        elementHelper.scrollIntoView(locator);
    }

    protected void waitForUrlContains(String text) {
        waitHelper.waitForUrlContains(text);
    }
}