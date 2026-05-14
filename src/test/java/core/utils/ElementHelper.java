package core.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ElementHelper {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    private static final Logger logger =
            LoggerHelper.getLogger(ElementHelper.class);

    public ElementHelper(WebDriver driver, int timeout) {

        this.driver = driver;

        this.waitHelper =
                new WaitHelper(driver);
    }

    public void click(By locator) {

        try {

            logger.info("Clicking element: {}", locator);

            WebElement element =
                    waitHelper.waitForClickability(locator);

            element.click();

        } catch (ElementClickInterceptedException e) {

            logger.warn(
                    "Normal click failed. Using JS click for: {}",
                    locator
            );

            WebElement element =
                    waitHelper.waitForClickability(locator);

            scrollIntoView(locator);

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            element
                    );
        }
    }

    public void type(By locator, String text) {

        logger.info(
                "Typing text into element: {}",
                locator
        );

        WebElement element =
                waitHelper.waitForVisibility(locator);

        element.clear();

        element.sendKeys(text);
    }

    public String getText(By locator) {

        logger.info(
                "Getting text from element: {}",
                locator
        );

        return waitHelper
                .waitForVisibility(locator)
                .getText();
    }

    public boolean isDisplayed(By locator) {

        try {

            return waitHelper
                    .waitForVisibility(locator)
                    .isDisplayed();

        } catch (Exception e) {

            logger.warn(
                    "Element not displayed: {}",
                    locator
            );

            return false;
        }
    }

    public void selectByVisibleText(
            By locator,
            String text
    ) {

        logger.info(
                "Selecting value '{}' from dropdown: {}",
                text,
                locator
        );

        Select select =
                new Select(
                        waitHelper.waitForVisibility(locator)
                );

        select.selectByVisibleText(text);
    }

    public void jsClick(By locator) {

        logger.info(
                "Executing JS click on element: {}",
                locator
        );

        WebElement element =
                waitHelper.waitForVisibility(locator);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        element
                );
    }

    public void scrollIntoView(By locator) {

        logger.info(
                "Scrolling to element: {}",
                locator
        );

        WebElement element =
                waitHelper.waitForVisibility(locator);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        element
                );
    }

    public String getAttribute(
            By locator,
            String attributeName
    ) {

        logger.info(
                "Getting attribute '{}' from element: {}",
                attributeName,
                locator
        );

        return waitHelper
                .waitForVisibility(locator)
                .getAttribute(attributeName);
    }

    public boolean isEnabled(By locator) {

        return waitHelper
                .waitForVisibility(locator)
                .isEnabled();
    }

    public boolean isSelected(By locator) {

        return waitHelper
                .waitForVisibility(locator)
                .isSelected();
    }
}