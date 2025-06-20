package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.TopNavigationBar;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public DriverManager openLandingPage(){
        driver.get("http://www.automationpractice.pl/index.php");
        new TopNavigationBar(driver);
        return this;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
            instance = null;
        }
    }
}
