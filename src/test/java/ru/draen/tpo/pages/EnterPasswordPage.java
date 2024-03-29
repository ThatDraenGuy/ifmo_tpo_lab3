package ru.draen.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EnterPasswordPage extends Page {
    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//input[@class=\"hidden-input\"]")
    public WebElement hiddenUsername;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//input[@type=\"password\"]")
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//button[@type=\"submit\"]")
    public WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-signin\"]//div[@role=\"alert\"]")
    public WebElement alert;

    public EnterPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void tryLogin(String password) {
        passwordField.sendKeys(password);
        signInButton.click();
    }
}
