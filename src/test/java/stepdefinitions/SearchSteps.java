package stepdefinitions;

import io.cucumber.java.en.*;
import pages.SearchPage;

public class SearchSteps {

    SearchPage searchPage;

    @Given("User is on search page")
    public void user_is_on_search_page() {

    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {

        searchPage.enterSearch(product);
        searchPage.clickSearch();
    }

    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {

    }
}