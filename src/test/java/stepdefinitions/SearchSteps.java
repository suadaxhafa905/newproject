package stepdefinitions;

import core.config.ConfigReader;
import core.driver.DriverFactory;
import io.cucumber.java.en.*;
import pages.SearchPage;

public class SearchSteps {

    SearchPage searchPage;

    @Given("User is on search page")
    public void userIsOnSearchPage() {

        searchPage = new SearchPage(
                DriverFactory.getDriver()
        );

        searchPage.open(
                ConfigReader.get("baseUrl") + "/search"
        );
    }

    @When("User searches for {string}")
    public void userSearchesFor(String product) {

        searchPage.searchProduct(product);
    }

    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() {

    }
}