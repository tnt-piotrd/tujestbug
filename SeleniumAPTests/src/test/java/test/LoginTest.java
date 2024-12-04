package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.TopNavigationBar;

import static test.BaseTest.*;

public class LoginTest {
    private WebDriver driver;
    private TopNavigationBar topNavigationBar;
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://automationpractice.pl/index.php");
        topNavigationBar = new TopNavigationBar(driver);
        loginPage = topNavigationBar.openLoginPage();
    }

    @Test
    public void successfulLoginTest() {
        loginPage.successfulLogin(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        Assert.assertEquals(topNavigationBar.getUserName(), "%s %s".formatted(USER_NAME, USER_SURNAME));
    }

    @Test(dataProvider = "invalid-credentials")
    public void unsuccessfulLoginTest(String email, String password, String errorMessage) {
        loginPage.unsuccessfulLogin(email, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(errorMessage),
                String.format("Expected '%s' message to contain '%s'", loginPage.getErrorMessage(), errorMessage));
    }

    @DataProvider(name = "invalid-credentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][]{
                {"abcd@aa.com", "1234W", "Authentication failed"},
                {"test@email", "1231AA", "Invalid email address"},
                {"test@email.pl", "123", "Invalid password"}
        };
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
