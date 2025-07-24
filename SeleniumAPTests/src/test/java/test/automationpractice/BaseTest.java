package test.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.TopNavigationBar;
import utils.ConfigurationLoader;

public class BaseTest {
    public static final String VALID_USER_LOGIN = "tjb@info.com";
    public static final String VALID_USER_PASSWORD = "1234Test!@!";
    protected static final String USER_NAME = "Piotr";
    protected static final String USER_SURNAME = "Testowy";
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void loginToShop() {
        ChromeOptions options = new ChromeOptions();
        if (ConfigurationLoader.getInstance().isHeadless()){
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=%s".formatted(ConfigurationLoader.getInstance().getResolution()));
        driver = new ChromeDriver(options);
        driver.get(ConfigurationLoader.getInstance().getBaseUrl());
        new TopNavigationBar(driver).openLoginPage().successfulLogin(VALID_USER_LOGIN, VALID_USER_PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void logoutFromShop() {
        new TopNavigationBar(driver).logout();
        driver.quit();
    }
}
