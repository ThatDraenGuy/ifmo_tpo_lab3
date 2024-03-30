package ru.draen.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;

import ru.draen.tpo.pages.HomePage;

public class HomePageTest extends PageTestBase {
    private HomePage homePage;

    @TestWithAllDrivers
    public void emptySearch(WebDriver driver) {
        homePage.searchButton.click();
        assertEquals("Enter a destination to start searching.", homePage.searchAlert.getText().trim());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        homePage = HomePage.initialize(driver);
    }

}
