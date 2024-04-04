package org.example;

import org.example.model.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;


public class MainPageTest {

    private MainPage mainPage;

    public void setUpDriver(WebDriver driver) {
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @MethodSource("driverProvider")
    void loginTest(WebDriver driver) {
        setUpDriver(driver);
        mainPage.getLoginHref().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(Utils.BASE_URL + "login/"));
        driver.quit();
    }

    public static Stream<Object[]> driverProvider() {
        WebDriver cDriver = new ChromeDriver();
        cDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        cDriver.get(Utils.BASE_URL);

        WebDriver fDriver = new FirefoxDriver();
        fDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fDriver.get(Utils.BASE_URL);

        return Stream.of(
                new Object[] {cDriver},
                new Object[] {fDriver}
        );
    }

}