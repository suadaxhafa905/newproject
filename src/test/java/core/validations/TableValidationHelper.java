package core.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableValidationHelper {

    private final WebDriver driver;

    public TableValidationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isValuePresentInColumn(
            By rowsLocator,
            String expectedValue
    ) {

        List<WebElement> rows =
                driver.findElements(rowsLocator);

        List<String> values =
                new ArrayList<>();

        for (WebElement row : rows) {

            values.add(
                    row.getText().trim()
            );
        }

        return values.contains(expectedValue);
    }

    public int getRowCount(By rowsLocator) {

        return driver
                .findElements(rowsLocator)
                .size();
    }

    public boolean isTableEmpty(By rowsLocator) {

        return getRowCount(rowsLocator) == 0;
    }
}