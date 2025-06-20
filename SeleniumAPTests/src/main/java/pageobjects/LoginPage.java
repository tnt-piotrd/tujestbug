package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailInputBy = By.id("email");
    private final By passwordInputBy = By.id("passwd");
    private final By signInButtonBy = By.id("SubmitLogin");
    private final By errorTextBy = By.xpath("//div[@id='center_column']/div[@class='alert alert-danger']");
    private final By createAccountEmailInputBy = By.id("email_create");
    private final By createAccountButtonBy = By.id("SubmitCreate");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage unsuccessfulLogin(String email, String password) {
        driver.findElement(emailInputBy).sendKeys(email);
        driver.findElement(passwordInputBy).sendKeys(password);
        driver.findElement(signInButtonBy).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorTextBy));
        return this;
    }

    public MyAccountPage successfulLogin(String email, String password) {
        driver.findElement(emailInputBy).sendKeys(email);
        driver.findElement(passwordInputBy).sendKeys(password);
        driver.findElement(signInButtonBy).click();
        return new MyAccountPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorTextBy).getText();
    }

    public CreateAccountPage openCreationAccountPage(String email){
        driver.findElement(createAccountEmailInputBy).sendKeys(email);
        driver.findElement(createAccountButtonBy).click();
        return new CreateAccountPage(driver);
    }
}
