package stepdefinitions;


import core.config.ConfigReader;
import core.driver.DriverFactory;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    @Given("I login to the system")
    public void loginToSystem() {

        loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.open(ConfigReader.get("baseUrl"));
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }
}

