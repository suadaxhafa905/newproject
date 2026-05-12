package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    private final By searchBox =
            By.id("search");

    private final By searchButton =
            By.id("searchBtn");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        openUrl(url);
    }

    public void enterSearch(String product) {
        type(searchBox, product);
    }

    public void clickSearch() {
        click(searchButton);
    }

    public void searchProduct(String product) {

        enterSearch(product);

        clickSearch();
    }
}