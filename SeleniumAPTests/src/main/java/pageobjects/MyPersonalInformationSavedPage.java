package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.TimeOuts._30_SECONDS;

public class MyPersonalInformationSavedPage extends BasePage {
    private static final String CONFIRMATION_LABEL_XPATH = "//p[@class='alert alert-success']";

    @FindBy(xpath = "//a[contains(@href, 'my-account')][contains(@class, 'button-small')]")
    private WebElement backToAccountButton;

    @FindBy(xpath = CONFIRMATION_LABEL_XPATH)
    private WebElement confirmationLabel;

    public MyPersonalInformationSavedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFIRMATION_LABEL_XPATH)));
    }

    public MyPersonalInformationPage goBackToYourAccount() {
        backToAccountButton.click();
        return new MyPersonalInformationPage(driver);
    }

    public String getConfirmationMessage() {
        return confirmationLabel.getText();
    }
}
