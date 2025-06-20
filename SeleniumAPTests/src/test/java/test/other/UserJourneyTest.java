package test.other;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;
import test.automationpractice.BaseTest;
import test.singleton.BaseSingletonTest;
import utils.DriverManager;

import static org.testng.Assert.assertTrue;

public class UserJourneyTest extends BaseSingletonTest {
    private WebDriver driver;

    @BeforeMethod
    public void setupDriver() {
        driver = DriverManager.getInstance().getDriver();
    }

    @Test
    public void userLoginSuccessfully() {
        new TopNavigationBar(driver).openLoginPage()
                .successfulLogin(BaseTest.VALID_USER_LOGIN, BaseTest.VALID_USER_PASSWORD);
    }

    @Test(dependsOnMethods = {"userLoginSuccessfully"})
    public void cartShouldBeEmptyWhenNewSessionBegins() {
        String cartStatus = new TopNavigationBar(driver).getCartStatus();
        Assert.assertTrue(cartStatus.contains("(empty)"), "Cart was not empty !");
    }

    @Test(dependsOnMethods = {"userLoginSuccessfully", "cartShouldBeEmptyWhenNewSessionBegins"})
    public void thereShouldBeNoSpecifiedAddressForNewUser(){
        assertTrue(new TopNavigationBar(driver).openUserAccountDetails()
                .openMyAddresses().isLabelForNoAddressesDisplayed());
    }

    @Test(dependsOnMethods = {"userLoginSuccessfully", "cartShouldBeEmptyWhenNewSessionBegins", "thereShouldBeNoSpecifiedAddressForNewUser"})
    public void userShouldSuccessfullyLogout(){
        new TopNavigationBar(driver).logout();
    }
}
