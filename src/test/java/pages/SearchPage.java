package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    By searchBox = By.id("search");
    By searchButton = By.id("searchBtn");

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterSearch(String product){
        driver.findElement(searchBox).sendKeys(product);
    }

    public void clickSearch(){
        driver.findElement(searchButton).click();
    }
}