package test.singleton;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.DriverManager;

public class BaseSingletonTest {
    @BeforeSuite
    public void startDriverAndOpenLandingPage() {
        DriverManager.getInstance().openLandingPage();
    }

    @AfterSuite
    public void logout() {
        DriverManager.getInstance().quit();
    }
}
