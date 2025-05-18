package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openShopApp() {
        driver = new ChromeDriver();
        driver.get("http://www.automationpractice.pl/index.php");
    }

    @AfterMethod
    public void closeShopApp() {
        driver.quit();
    }

    @Test
    public void invalidPasswordTest() {
        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage("tjb1@info.com")
                .registerUnsuccessfully(true, "John", "Doe", "tjb1@info.com", "1234",
                "1", "10", "2000", false)
                .getErrorMessages();
        assertThat(errorMessages, hasItem("passwd is invalid."));
    }

    @Test
    public void notEnoughDataTest() {
        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage("tjb1@info.com")
                .registerUnsuccessfully(null, "", "", "tjb1@info.com", "1234678",
                        "10", "1", "1984", true)
                .getErrorMessages();
        assertThat(errorMessages, hasItems("lastname is required.", "firstname is required."));
    }

    @Test
    public void invalidNameTest(){
        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage("tjb1@info.com")
                .registerUnsuccessfully(false, "Eve123", "Smith", "tjb1@info.com", "1234678",
                        "10", "1", "1950", null)
                .getErrorMessages();
        assertThat(errorMessages, hasItem("firstname is invalid."));
    }
}
