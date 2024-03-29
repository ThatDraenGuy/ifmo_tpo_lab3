package ru.draen.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 */
public class HomePage extends Page {
  @FindBy(how = How.XPATH, using = "//*[@data-testid=\"header-sign-in-button\"]")
  public WebElement signInButton;

  @FindBy(how = How.XPATH, using = "//*[@data-testid=\"header-sign-up-button\"]")
  public WebElement signUpButton;

  @FindBy(how = How.XPATH, using = "//*[@data-testid=\"header-profile\"]")
  public WebElement profileButton;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public void goToSignIn() {
    signInButton.click();
  }

  public void goToSignUp() {
    signUpButton.click();
  }
}
