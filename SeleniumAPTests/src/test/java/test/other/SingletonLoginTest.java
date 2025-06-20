package test.other;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.TopNavigationBar;
import test.singleton.BaseSingletonTest;
import utils.DriverManager;

public class SingletonLoginTest extends BaseSingletonTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        WebDriver driver = DriverManager.getInstance().getDriver();
        loginPage = new TopNavigationBar(driver).openLoginPage();
    }

    @Test
    public void authenticationFailureTest(){
        loginPage.unsuccessfulLogin("abcd@aa.com", "1234W");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Authentication failed"));
    }

    @Test
    public void invalidEmailAddressTest() {
        loginPage.unsuccessfulLogin("test@email", "1231AA");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid email address"));
    }

    @Test
    public void invalidPasswordTest() {
        loginPage.unsuccessfulLogin("test@email.pl", "123");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid password"));
    }
}
