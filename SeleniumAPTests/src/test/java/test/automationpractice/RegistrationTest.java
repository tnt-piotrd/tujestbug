package test.automationpractice;

import dao.CreateAccountDAO;
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
    private static final String INVALID_EMAIL_ADDRESS = "tjb1@info.com";
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
        CreateAccountDAO createAccountDAO = CreateAccountDAO.builder()
                .isMr(true)
                .firstName("John")
                .lastName("Doe")
                .email(INVALID_EMAIL_ADDRESS)
                .password("1234")
                .dateOfBirthDays("1")
                .dateOfBirthMonths("10")
                .dateOfBirthYears("2000")
                .isNewsletter(false)
                .build();

        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage(INVALID_EMAIL_ADDRESS)
                .registerUnsuccessfully(createAccountDAO)
                .getErrorMessages();
        assertThat(errorMessages, hasItem("passwd is invalid."));
    }

    @Test
    public void notEnoughDataTest() {
        CreateAccountDAO createAccountDAO = CreateAccountDAO.builder()
                .isMr(null)
                .firstName("")
                .lastName("")
                .email(INVALID_EMAIL_ADDRESS)
                .password("1234678")
                .dateOfBirthDays("10")
                .dateOfBirthMonths("1")
                .dateOfBirthYears("1984")
                .isNewsletter(true)
                .build();

        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage(INVALID_EMAIL_ADDRESS)
                .registerUnsuccessfully(createAccountDAO)
                .getErrorMessages();
        assertThat(errorMessages, hasItems("lastname is required.", "firstname is required."));
    }

    @Test
    public void invalidNameTest(){
        CreateAccountDAO createAccountDAO = CreateAccountDAO.builder()
                .isMr(false)
                .firstName("Eve123")
                .lastName("Smith")
                .email(INVALID_EMAIL_ADDRESS)
                .password("1234678")
                .dateOfBirthDays("10")
                .dateOfBirthMonths("1")
                .dateOfBirthYears("1950")
                .isNewsletter(null)
                .build();

        List<String> errorMessages = new TopNavigationBar(driver).openLoginPage()
                .openCreationAccountPage(INVALID_EMAIL_ADDRESS)
                .registerUnsuccessfully(createAccountDAO)
                .getErrorMessages();
        assertThat(errorMessages, hasItem("firstname is invalid."));
    }
}
