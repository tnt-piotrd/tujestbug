package pageobjects;

import dao.CreateAccountDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.FieldUtils.*;
import static utils.TimeOuts._30_SECONDS;

public class CreateAccountPage extends BasePage{

    private static final String REGISTER_ACCOUNT_BUTTON_XPATH = "//button[@name='submitAccount']";

    @FindBy(id = "id_gender1")
    private WebElement mrRadioButton;

    @FindBy(id = "id_gender2")
    private WebElement mrsRadioButton;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daysSelect;

    @FindBy(id = "months")
    private WebElement monthsSelect;

    @FindBy(id = "years")
    private WebElement yearsSelect;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = REGISTER_ACCOUNT_BUTTON_XPATH)
    private WebElement registerAccountButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-danger')]//li")
    private List<WebElement> errorMessagesLabels;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.elementToBeClickable(By.xpath(REGISTER_ACCOUNT_BUTTON_XPATH)));
    }

    public CreateAccountPage registerUnsuccessfully(CreateAccountDAO createAccountDAO) {
        selectRadioButtonIfNotEmpty(mrRadioButton, mrsRadioButton, createAccountDAO.getIsMr());
        enterTextIfNotEmpty(firstNameInput, createAccountDAO.getFirstName());
        enterTextIfNotEmpty(lastNameInput, createAccountDAO.getLastName());
        enterTextIfNotEmpty(emailInput, createAccountDAO.getEmail());
        enterTextIfNotEmpty(passwordInput, createAccountDAO.getPassword());
        selectDateOfBirth(createAccountDAO.getDateOfBirthDays(), createAccountDAO.getDateOfBirthMonths(), createAccountDAO.getDateOfBirthYears());
        selectCheckboxIfNotEmpty(newsletterCheckbox, createAccountDAO.getIsNewsletter());
        registerAccountButton.click();
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.visibilityOfAllElements(errorMessagesLabels));
        return this;
    }

    private void selectDateOfBirth(String day, String month, String year) {
        selectStateIfNotEmpty(daysSelect, day);
        selectStateIfNotEmpty(monthsSelect, month);
        selectStateIfNotEmpty(yearsSelect, year);
    }

    public List<String> getErrorMessages() {
        return errorMessagesLabels.stream().map(WebElement::getText).toList();
    }
}