package ru.draen.tpo.tests;

import org.openqa.selenium.WebDriver;
import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SearchResultPage;
import org.openqa.selenium.NoSuchElementException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest extends PageTestBase {
    private HomePage homePage;

    @TestWithAllDrivers
    public void emptySearch(WebDriver driver) {
        homePage.searchButton.click();
        assertEquals("Enter a destination to start searching.", homePage.searchAlert.getText().trim());
    }

    @TestWithAllDrivers
    public void searchForForbiddenRegion(WebDriver driver) {
        homePage.searchForCity(Constants.FORBIDDEN_CITY);
        SearchResultPage resultPage = SearchResultPage.initialize(driver);
        assertTrue(resultPage.getTitle().contains("Hotels in " + Constants.FORBIDDEN_CITY));
        assertTrue(resultPage.russiaBlackoutBanner.isDisplayed());
        assertEquals(resultPage.russiaBlackoutBanner.getText(), "It's not possible to make a reservation for this region through Booking.com at this time.");
    }

    @TestWithAllDrivers
    public void searchForNormalRegion(WebDriver driver) {
        homePage.searchForCity(Constants.ALLOWED_CITY, true);
        SearchResultPage resultPage = SearchResultPage.initialize(driver);
        assertTrue(resultPage.getTitle().contains("Hotels in " + Constants.ALLOWED_CITY));
        assertThrows(NoSuchElementException.class, () -> {
            resultPage.russiaBlackoutBanner.isDisplayed();
        });
        String resultText = resultPage.resultsHeader.getText();
        System.out.println("ABABA " + resultText);
        Pattern p = Pattern.compile(Constants.ALLOWED_CITY +": ([0-9,]+) properties found");
        Matcher m = p.matcher(resultText);
        assertTrue(m.matches());
        assertTrue(Integer.parseInt(m.group(1).replace(",", "")) > 0);
    }

    @Override
    protected void preparePages(WebDriver driver) {
        homePage = HomePage.initialize(driver);
    }

}
