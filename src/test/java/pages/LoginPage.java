package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators

    private final By openLoginButton =
            By.xpath("//a[contains(text(),'Login')]");


    private final By usernameInput =
            By.id("username");

    private final By passwordInput =
            By.id("password");

    private final By loginButton =
            By.xpath("//form[@id='login-form']//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        openUrl(url);
    }


    public void clickOpenLogin() {
        click(openLoginButton);
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

        clickOpenLogin();

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}