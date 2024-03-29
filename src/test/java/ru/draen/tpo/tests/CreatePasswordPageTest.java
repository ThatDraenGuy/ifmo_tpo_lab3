package ru.draen.tpo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.draen.tpo.Constants;
import ru.draen.tpo.pages.CreatePasswordPage;
import ru.draen.tpo.pages.HomePage;
import ru.draen.tpo.pages.SignInPage;

public class CreatePasswordPageTest extends PageTestBase {
    private CreatePasswordPage createPasswordPage;

    private void testWrongPassword(String password, String expectedAlert) {
        createPasswordPage.tryCreatePassword(password);
        assertEquals(expectedAlert, createPasswordPage.alert.getText().trim());
    }

    @TestWithAllDrivers
    public void passwordTooShort(WebDriver webDriver) {
        testWrongPassword(Constants.PASSWORD_TOO_SHORT, "Your password must be at least 10 characters");
    }

    @TestWithAllDrivers
    public void passwordNoNumbers(WebDriver webDriver) {
        testWrongPassword(Constants.PASSWORD_NO_NUMBERS, "Your password must include at least one number");
    }

    @TestWithAllDrivers
    public void passwordNoUppercase(WebDriver webDriver) {
        testWrongPassword(Constants.PASSWORD_NO_UPPERCASE, "Your password must include at least one uppercase letter");
    }

    @TestWithAllDrivers
    public void passwordNoLowercase(WebDriver webDriver) {
        testWrongPassword(Constants.PASSWORD_NO_LOWERCASE, "Your password must include at least one lowercase letter");
    }

    @TestWithAllDrivers
    public void passwordMismatch(WebDriver webDriver) {
        createPasswordPage.tryCreatePassword(Constants.PASSWORD_NO_LOWERCASE, Constants.PASSWORD_PASSING);
        assertEquals("The passwords you entered didn't match – try again", createPasswordPage.secondAlert.getText().trim());
    }

    @TestWithAllDrivers
    public void passwordPassing(WebDriver webDriver) {
        createPasswordPage.passwordField.sendKeys(Constants.PASSWORD_PASSING);
        createPasswordPage.confirmPasswordField.sendKeys(Constants.PASSWORD_PASSING);
        assertThrows(NoSuchElementException.class, () -> {
            createPasswordPage.alert.getText();
            createPasswordPage.secondAlert.getText();
        });
    }

    @Override
    protected void preparePages(WebDriver driver) {
        HomePage homePage = HomePage.initialize(driver);
        homePage.goToSignIn();
        SignInPage signInPage = SignInPage.initialize(driver);
        signInPage.tryLogin(Constants.NEW_EMAIL);
        createPasswordPage = CreatePasswordPage.initialize(driver);
    }

}
