package stepdefinitions;

import core.driver.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;
import org.testng.asserts.SoftAssert;

public class RegisterSteps {

    WebDriver driver = DriverFactory.getDriver();

    RegisterPage registerPage = new RegisterPage(driver);

    @Given("User is on register page")
    public void user_is_on_register_page() {

        driver.get("https://thedummysite.com/signup");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {

        registerPage.enterUsername(username);
    }

    @And("User enters email {string}")
    public void user_enters_email(String email) {

        registerPage.enterEmail(email);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {

        registerPage.enterPassword(password);
    }

    @And("User enters confirm password {string}")
    public void user_enters_confirm_password(String confirmPassword) {

        registerPage.enterConfirmPassword(confirmPassword);
    }

    @And("User clicks create account button")
    public void user_clicks_create_account_button() {

        registerPage.clickCreateAccount();
    }

    @Then("Account should be created successfully")
    public void account_should_be_created_successfully() {

        System.out.println("Account created successfully");
    }

    @Then("Error messages should be displayed")
    public void error_messages_should_be_displayed() {

        SoftAssert softAssert = new SoftAssert();

        String actualEmailError =
                registerPage.getEmailError();

        String actualPasswordError =
                registerPage.getPasswordError();

        softAssert.assertEquals(
                actualEmailError,
                "Invalid email"
        );

        softAssert.assertEquals(
                actualPasswordError,
                "Passwords do not match"
        );

        // shumë e rëndësishme
        softAssert.assertAll();
    }
}