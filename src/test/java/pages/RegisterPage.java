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

    public String getPasswordError() {
        return getText(passwordError);
    }
    public String getPageText() {
        return driver.getPageSource();
    }
}