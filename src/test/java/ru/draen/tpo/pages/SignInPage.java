package ru.draen.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends Page {
  @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//input[@type=\"email\"]")
  public WebElement emailField;

  @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//button[@type=\"submit\"]")
  public WebElement submitButton;

  @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//div[@role=\"alert\"]")
  public WebElement alert;

  public SignInPage(WebDriver webDriver) {
    super(webDriver);
  }

  public static SignInPage initialize(WebDriver driver) {
    return Page.initialize(driver, "//form[@class=\"nw-signin\"]//button[@type=\"submit\"]", SignInPage.class);
  }

  public void tryLogin(String email) {
    emailField.sendKeys(email);
    submitButton.click();
  }

}