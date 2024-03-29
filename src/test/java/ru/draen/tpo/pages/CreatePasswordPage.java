package ru.draen.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class CreatePasswordPage extends Page {
    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-register\"]//input[@class=\"hidden-input\"]")
    public WebElement hiddenUsername;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-register\"]//input[@id=\"new_password\"]")
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-register\"]//input[@id=\"confirmed_password\"]")
    public WebElement confirmPasswordField;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-register\"]//button[@type=\"submit\"]")
    public WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//form[@class=\"nw-register\"]//div[@role=\"alert\"]")
    public WebElement alert;

    @FindBy(how = How.XPATH, using = "(//form[@class=\"nw-register\"]//div[@role=\"alert\"])[2]")
    public WebElement secondAlert;

    public CreatePasswordPage(WebDriver driver) {
        super(driver);
    }

    public void tryCreatePassword(String password) {
        tryCreatePassword(password, password);
    }

    public void tryCreatePassword(String password, String confirm) {
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirm);
        submitButton.click();
    }

}
