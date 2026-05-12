package stepdefinitions;

import core.config.ConfigReader;
import core.driver.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

public class RegisterSteps {

    RegisterPage registerPage;
    SoftAssert softAssert;

    @Given("User is on register page")
    public void userIsOnRegisterPage() {

        registerPage = new RegisterPage(
                DriverFactory.getDriver()
        );

        softAssert = new SoftAssert();

        registerPage.open(
                ConfigReader.get("baseUrl") + "/signup"
        );
    }

    @When("User enters username {string}")
    public void userEntersUsername(String username) {

        registerPage.enterUsername(username);
    }

    @And("User enters email {string}")
    public void userEntersEmail(String email) {

        registerPage.enterEmail(email);
    }

    @And("User enters password {string}")
    public void userEntersPassword(String password) {

        registerPage.enterPassword(password);
    }

    @And("User enters confirm password {string}")
    public void userEntersConfirmPassword(String confirmPassword) {

        registerPage.enterConfirmPassword(confirmPassword);
    }

    @And("User clicks create account button")
    public void userClicksCreateAccountButton() {

        registerPage.clickCreateAccount();
    }

    @Then("Account should be created successfully")
    public void accountShouldBeCreatedSuccessfully() {

        System.out.println("Account created successfully");
    }

    @Then("Error messages should be displayed")
    public void errorMessagesShouldBeDisplayed() {

 //       System.out.println(registerPage.getPageText());

/*
        softAssert.assertEquals(
                registerPage.getEmailError(),
                "Invalid email"
        );

 */

        softAssert.assertTrue(
                registerPage.getPasswordError()
                        .contains("Passwords do not match"),
                "Password error message is not displayed correctly"
        );

        softAssert.assertAll();
    }
}