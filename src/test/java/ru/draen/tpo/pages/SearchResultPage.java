package ru.draen.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public void waitForLoaders() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(d -> !hasLoaders());
    }

    public void expandAllFilters() {
        driver.findElements(By.xpath("//button[@data-testid=\"filters-group-expand-collapse\"]"))
                .forEach(button -> {
                    try {
                        button.click();
                    } catch (Exception ignored) {}
                });
    }

    public void toggleFilterByRating(String filterCategory, int filterValue) {
        WebElement element = driver.findElement(By.xpath("//div[contains(@data-filters-item, \"" + filterCategory + "=" + filterValue + "\")]"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(d -> {
            WebElement elem = driver.findElement(By.xpath("//div[@id=\"onetrust-policy-text\"]"));
            try {
                return !elem.isDisplayed();
            } catch (NoSuchElementException e) {
                return true;
            }
        });
        element.click();
        waitForLoaders();
    }

    public List<Integer> getAllOfferRatings() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[*[@data-testid=\"rating-stars\"]]"));
        Pattern p = Pattern.compile("([0-9]) out of [0-9]");
        return elements.stream().map(el -> {
            Matcher m = p.matcher(el.getAttribute("aria-label"));
            if (m.matches()) {
                return Integer.parseInt(m.group(1));
            } else return 0;
        }).collect(Collectors.toList());
    }

    public boolean hasLoaders() {

        try {
            WebElement element = driver.findElement(By.xpath("//*[@data-testid=\"skeleton-loader-card\"]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<WebElement> getOffersButtons() {
        return driver.findElements(By.xpath("//div[@data-testid=\"title\"]"));
    }
}
