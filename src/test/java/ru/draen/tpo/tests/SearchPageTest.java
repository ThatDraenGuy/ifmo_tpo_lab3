package ru.draen.tpo.tests;

import org.openqa.selenium.WebDriver;

import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SearchResultPage;

public class SearchPageTest extends PageTestBase {
    SearchResultPage searchResultPage;

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.searchForCity(Constants.ALLOWED_CITY, true);
        searchResultPage = SearchResultPage.initialize(driver);
    }

}
