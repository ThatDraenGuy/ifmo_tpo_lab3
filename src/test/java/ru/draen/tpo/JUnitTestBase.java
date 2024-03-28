package ru.draen.tpo;


import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for all the JUnit-based test classes
 */
public abstract class JUnitTestBase {
  protected static String baseUrl;
  protected static List<Capabilities> capabilities;

  @ParameterizedTest(name = "{0}")
  @MethodSource("allImplementations")
  @Retention(RetentionPolicy.RUNTIME)
  protected @interface TestAllImplementations {
  }

  static Stream<Arguments> allImplementations() {
    System.out.println("there");
    return capabilities.stream().map(caps -> Arguments.of(WebDriverPool.DEFAULT.getDriver(caps)));
  }

  @BeforeAll
  public static void loadConfig() throws Throwable {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    capabilities = config.getCapabilities();
  };
}
