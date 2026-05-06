package pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final SelfHealingDriver driver;
    private final WebDriverWait wait;

    private final By button = By.xpath("//a[text()='Login']");
    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By loginBtn = By.className("btn-submit");

    public LoginPage(SelfHealingDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String user, String pass) {
        driver.findElement(button).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}