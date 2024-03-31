package ru.draen.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.EnterPasswordPage;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SignInPage;

public class EnterPasswordPageTest extends PageTestBase {
    private EnterPasswordPage enterPasswordPage;

    @TestWithAllDrivers
    public void passwordWrong(WebDriver driver) {
        enterPasswordPage.tryLogin(Constants.PASSWORD_PASSING);
        assertEquals("The email and password combination entered doesn't match.", enterPasswordPage.alert.getText().trim());
    }

    @TestWithAllDrivers
    public void passwordCorrect(WebDriver driver) {
        enterPasswordPage.tryLogin(Constants.EXISTING_PASSWORD);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertDoesNotThrow(() -> {
            homePage.profileButton.getText();
        });
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.goToSignIn();
        SignInPage signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.EXISTING_EMAIL);
        enterPasswordPage = EnterPasswordPage.initialize(driver);
    }

}
