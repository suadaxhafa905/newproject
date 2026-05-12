package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameInput =
            By.id("username");

    private final By passwordInput =
            By.id("password");

    private final By loginButton =
            By.id("loginButton");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        openUrl(url);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}