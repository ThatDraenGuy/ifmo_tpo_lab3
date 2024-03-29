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

  public void tryLogin(String email) {
    emailField.sendKeys(email);
    submitButton.click();
  }

}