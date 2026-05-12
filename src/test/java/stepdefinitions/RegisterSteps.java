package stepdefinitions;

import core.assertions.SoftAssertHelper;
import core.config.ConfigReader;
import core.driver.DriverFactory;
import core.utils.ExcelReader;
import io.cucumber.java.en.*;
import pages.RegisterPage;

import java.util.Map;

public class RegisterSteps {

    RegisterPage registerPage;
    SoftAssertHelper softAssert;

    @Given("User is on register page")
    public void userIsOnRegisterPage() {

        registerPage = new RegisterPage(
                DriverFactory.getDriver()
        );

        softAssert = new SoftAssertHelper();

        registerPage.open(
                ConfigReader.get("baseUrl") + "/signup"
        );
    }

    @When("User fills register form with test data {string}")
    public void userFillsRegisterFormWithTestData(String testCase) {

        Map<String, String> data =
                ExcelReader.getTestData(
                        "src/test/resources/testdata/RegisterData.xlsx",
                        "Register",
                        testCase
                );

        registerPage.enterUsername(data.get("username"));
        registerPage.enterEmail(data.get("email"));
        registerPage.enterPassword(data.get("password"));
        registerPage.enterConfirmPassword(data.get("confirmPassword"));
    }

    @And("User clicks create account button")
    public void userClicksCreateAccountButton() {

        registerPage.clickCreateAccount();
    }

    @Then("Register error message should be displayed for test data {string}")
    public void registerErrorMessageShouldBeDisplayedForTestData(String testCase) {

        Map<String, String> data =
                ExcelReader.getTestData(
                        "src/test/resources/testdata/RegisterData.xlsx",
                        "Register",
                        testCase
                );

        String actualMessage =
                registerPage.getPasswordError();

        softAssert.assertTrue(
                testCase,
                "Validate register error message",
                actualMessage.contains(data.get("expectedMessage")),
                data.get("expectedMessage"),
                actualMessage,
                "HIGH"
        );

        softAssert.assertAll();
    }
}