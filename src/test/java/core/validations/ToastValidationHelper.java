package core.validations;

import core.assertions.SoftAssertHelper;
import core.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToastValidationHelper {

    private final ElementHelper elementHelper;
    private final SoftAssertHelper softAssert;

    public ToastValidationHelper(
            WebDriver driver,
            SoftAssertHelper softAssert
    ) {
        this.elementHelper = new ElementHelper(driver, 10);
        this.softAssert = softAssert;
    }

    public void validateToastContains(
            String testCase,
            String expectedMessage,
            By toastLocator,
            String severity
    ) {
        String actualMessage =
                elementHelper.getText(toastLocator);

        softAssert.assertTrue(
                testCase,
                "Validate toast message",
                actualMessage.contains(expectedMessage),
                expectedMessage,
                actualMessage,
                severity
        );
    }
}