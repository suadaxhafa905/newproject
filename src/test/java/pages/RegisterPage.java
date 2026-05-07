package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;

    // Locators
    By usernameField = By.name("username");
    By emailField = By.name("email");
    By passwordField = By.name("password");
    By confirmPasswordField = By.name("confirmPassword");
    By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    By emailError = By.xpath("//span[contains(text(),'Invalid email')]");
    By passwordError = By.xpath("//span[contains(text(),'Passwords do not match')]");

    // Constructor
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword){
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickCreateAccount(){
   //     driver.findElement(createAccountButton).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createAccountButton
                        )
                );

        button.click();
    }

    public String getEmailError(){

        return driver.findElement(emailError).getText();
    }

    public String getPasswordError(){

        return driver.findElement(passwordError).getText();
    }
}