package ru.draen.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.CreatePasswordPage;
import ru.draen.tpo.pages.EnterPasswordPage;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SignInPage;

public class SignInPageTest extends PageTestBase {
    private SignInPage signInPage;

    @TestWithAllDrivers
    public void wrongEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.WRONG_EMAIL);
        assertEquals("Make sure the email address you entered is correct.", signInPage.alert.getText().trim());
    }

    @TestWithAllDrivers
    public void newUserEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.NEW_EMAIL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        CreatePasswordPage createPasswordPage = PageFactory.initElements(driver, CreatePasswordPage.class);
        assertEquals(Constants.NEW_EMAIL, createPasswordPage.hiddenUsername.getAttribute("value").trim());
    }

    @TestWithAllDrivers
    public void existingUserEmail(WebDriver driver) {
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        EnterPasswordPage enterPasswordPage = PageFactory.initElements(driver, EnterPasswordPage.class);
        assertEquals(Constants.EXISTING_EMAIL, enterPasswordPage.hiddenUsername.getAttribute("value").trim());
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.goToSignIn();
        signInPage = PageFactory.initElements(driver, SignInPage.class);
    }
}
