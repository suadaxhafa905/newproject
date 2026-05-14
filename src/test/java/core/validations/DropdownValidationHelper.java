package core.validations;

import core.assertions.SoftAssertHelper;
import core.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropdownValidationHelper {

    private final ElementHelper elementHelper;
    private final SoftAssertHelper softAssert;

    public DropdownValidationHelper(
            WebDriver driver,
            SoftAssertHelper softAssert
    ) {
        this.elementHelper = new ElementHelper(driver, 10);
        this.softAssert = softAssert;
    }

    public void selectAndValidateValue(
            String testCase,
            By dropdownLocator,
            String visibleText,
            By selectedValueLocator,
            String severity
    ) {
        elementHelper.selectByVisibleText(
                dropdownLocator,
                visibleText
        );

        String actualValue =
                elementHelper.getText(selectedValueLocator);

        softAssert.assertTrue(
                testCase,
                "Validate dropdown selected value",
                actualValue.contains(visibleText),
                visibleText,
                actualValue,
                severity
        );
    }
}