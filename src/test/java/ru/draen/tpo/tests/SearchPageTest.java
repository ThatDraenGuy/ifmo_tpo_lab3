package ru.draen.tpo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SearchResultPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageTest extends PageTestBase {
    SearchResultPage searchResultPage;

    @TestWithAllDrivers
    public void starsFilters(WebDriver driver) {
        for (int rating = 1; rating <= 5; rating++) {
            final int currentRating = rating;
            if (rating > 1)
                searchResultPage.toggleFilterByRating("class", rating - 1);
            searchResultPage.toggleFilterByRating("class", rating);
            assertTrue(searchResultPage.getAllOfferRatings().stream().allMatch(actualRating -> actualRating == currentRating));
        }
    }

    @TestWithAllDrivers
    public void hotelFacilities(WebDriver driver) {
        searchResultPage.expandAllFilters();
        // Toggle wifi
        searchResultPage.toggleFilterByRating("hotelfacility", 107);
        assertTrue(!searchResultPage.getOffersButtons().isEmpty());
        searchResultPage.getOffersButtons().get(0).click();
        new WebDriverWait(driver, 5).until(d -> {
            try {
                WebElement element = driver.findElement(By.xpath("//span[text()=\"Free WiFi\"]"));
                System.out.println(element);
                return true;
            } catch (NoSuchElementException e) {
                System.out.println("Did not find facility span...");
                return false;
            }
        });
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.searchForCity(Constants.ALLOWED_CITY, true);
        searchResultPage = SearchResultPage.initialize(driver);
        searchResultPage.acceptCookies();
        searchResultPage.closeSignIn();
    }

}
