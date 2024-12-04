package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.TopNavigationBar;

public class BaseTest {
    public static final String VALID_USER_LOGIN = "tjb@info.com";
    public static final String VALID_USER_PASSWORD = "1234Test!@!";
    protected static final String USER_NAME = "Piotr";
    protected static final String USER_SURNAME = "Testowy";
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void loginToShop() {
        driver = new ChromeDriver();
        driver.get("http://www.automationpractice.pl/index.php");
        new TopNavigationBar(driver).openLoginPage().successfulLogin(VALID_USER_LOGIN, VALID_USER_PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void logoutFromShop() {
        new TopNavigationBar(driver).logout();
        driver.quit();
    }
}
