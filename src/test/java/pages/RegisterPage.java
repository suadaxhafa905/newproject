package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By usernameField = By.name("username");
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By confirmPasswordField = By.name("confirmPassword");
    private final By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private final By passwordError = By.id("pw-match-msg");
    //private final By passwordError = By.xpath("//*[contains(text(),'Passwords do not match')]");
    private final By emailError = By.id("email-msg");

    private final By usernameError = By.id("username-msg");


    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        openUrl(url);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    public void clickCreateAccount() {
        click(createAccountButton);
    }
/*
    public String getEmailError() {
        return getText(emailError);
    }

 */

    public String getPageText() {
        return driver.getPageSource();
    }


    public String getErrorMessage(String errorType) {

        if (errorType == null) {
            throw new RuntimeException("errorType is missing from Excel");
        }

        switch (errorType.toLowerCase()) {

            case "password":
                return getText(passwordError);

            case "email":
                return getNativeValidationMessage(emailField);

            case "username":
                return getNativeValidationMessage(usernameField);

            default:
                throw new RuntimeException(
                        "Invalid error type: " + errorType
                );
        }
    }
/*
    private String getValidationMessage(By locator) {

        return (String) jsExecute("return arguments[0].validationMessage;", find(locator)
        );
    }

 */


    private String getNativeValidationMessage(By locator) {

        return (String)
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript(
                                "return arguments[0].validationMessage;",
                                driver.findElement(locator)
                        );
    }
}