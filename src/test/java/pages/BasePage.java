package pages;

import core.config.ConfigReader;
import core.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    protected ElementHelper elementHelper;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        int timeout =
                Integer.parseInt(
                        ConfigReader.get("timeout")
                );

        this.elementHelper =
                new ElementHelper(driver, timeout);
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected void click(By locator) {
        elementHelper.click(locator);
    }

    protected void type(By locator, String text) {
        elementHelper.type(locator, text);
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
}