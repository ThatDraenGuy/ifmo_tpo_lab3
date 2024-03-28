package ru.draen.tpo;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ru.draen.tpo.pages.HomePage;

public class SampleJUnitTest extends JUnitTestBase {
  private HomePage homepage;

  @TestAllImplementations
  public void testHomePageHasAHeader(WebDriver driver) {
    homepage = PageFactory.initElements(driver, HomePage.class);
    driver.get(baseUrl);
    Assertions.assertFalse("".equals(homepage.header.getText()));
  }
}
