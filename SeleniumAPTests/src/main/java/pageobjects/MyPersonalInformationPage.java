package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static utils.TimeOuts._30_SECONDS;

public class MyPersonalInformationPage extends BasePage {
    @FindBy(id = "firstname")
    private WebElement nameInput;

    @FindBy(id = "lastname")
    private WebElement surnameInput;

    @FindBy(id = "id_gender1")
    private WebElement mrRadioButton;

    @FindBy(id = "id_gender12")
    private WebElement mrsRadioButton;

    @FindBy(id = "old_passwd")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "//button[@name='submitIdentity']")
    private WebElement saveFormButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private List<WebElement> errorMessagesLabels;

    public MyPersonalInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.textMatches(By.xpath(NAVIGATION_BUTTON_XPATH)
                        , Pattern.compile(".*Your personal information.*")));
    }

    public MyPersonalInformationSavedPage updatePersonalDataSuccessfully(boolean isMr, String name, String surname, String currentPassword) {
        fillForm(isMr, name, surname, currentPassword);
        saveFormButton.click();
        return new MyPersonalInformationSavedPage(driver);
    }

    public MyPersonalInformationPage updatePersonalDataUnsuccessfully(boolean isMr, String name, String surname, String currentPassword) {
        fillForm(isMr, name, surname, currentPassword);
        saveFormButton.click();
        return new MyPersonalInformationPage(driver);
    }

    public List<String> getErrorMessages() {
        return errorMessagesLabels.stream().map(WebElement::getText).toList();
    }

    private void fillForm(boolean isMr, String name, String surname, String currentPassword) {
        if (isMr) {
            mrRadioButton.click();
        } else {
            mrsRadioButton.click();
        }
        enterTextWithClearing(nameInput, name);
        enterTextWithClearing(surnameInput, surname);
        currentPasswordInput.sendKeys(currentPassword);
    }

    private void enterTextWithClearing(WebElement input, String text) {
        if (!input.getText().equals(text)) {
            input.clear();
            input.sendKeys(text);
        }
    }
}
