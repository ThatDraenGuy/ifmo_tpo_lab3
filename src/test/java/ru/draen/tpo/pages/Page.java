package ru.draen.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
  }

  protected static<T> T initialize(WebDriver driver, String checkXpath, Class<T> clazz) {
    Wait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait.until(d -> {
            return d.findElement(By.xpath(checkXpath)).isDisplayed();
        });
        return PageFactory.initElements(driver, clazz);
  }


  public String getTitle() {
    return driver.getTitle();
  }

}
