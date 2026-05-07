package pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.utils.LoggerUtil;
import org.slf4j.Logger;

import java.time.Duration;

public class LoginPage {

    private final SelfHealingDriver driver;
    private final WebDriverWait wait;

    private static final Logger logger =
            LoggerUtil.getLogger(LoginPage.class);

    private final By button = By.xpath("//a[text()='Login']");
    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By loginBtn = By.className("btn-submit");

    public LoginPage(SelfHealingDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("LoginPage initialized");
    }

    public void open(String url) {
        logger.info("Opening URL: {}", url);
        driver.get(url);
    }

    public void login(String user, String pass) {
        logger.info("Starting login process");
        driver.findElement(button).click();
        logger.info("Clicked login button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        logger.info("Entered username");
        driver.findElement(password).sendKeys(pass);
        logger.info("Entered password");
        driver.findElement(loginBtn).click();
        logger.info("Clicked submit button");
    }
}