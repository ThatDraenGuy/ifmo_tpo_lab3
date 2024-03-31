package ru.draen.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPage extends Page {
    @FindBy(how = How.XPATH, using = "//div//aside[@data-testid=\"russia-blackout-banner\"]//p")
    public WebElement russiaBlackoutBanner;

    @FindBy(how = How.XPATH, using = "//h1[contains(@aria-label, \"Search results updated\")]")
    public WebElement resultsHeader;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public static SearchResultPage initialize(WebDriver driver) {
        return Page.initialize(driver, "//div[@data-testid=\"breadcrumbs\"]", SearchResultPage.class);
    }
}
