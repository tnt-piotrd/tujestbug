package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import other.singleton.LoggerSingleton;

import static utils.TimeOuts._30_SECONDS;

public class MyAccountPage extends BasePage {
    private static final String TITLE_LINK_XPATH = "//a[@title='Orders']";

    @FindBy(xpath = TITLE_LINK_XPATH)
    private WebElement orderHistoryButton;

    @FindBy(xpath = "//a[@title='Information']")
    private WebElement personalInformationButton;

    @FindBy(xpath = "//a[@title='Addresses']")
    private WebElement myAddressesButton;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration()).until(ExpectedConditions.elementToBeClickable(By.xpath(TITLE_LINK_XPATH)));
    }

    public MyPersonalInformationPage openMyPersonalInformation() {
        personalInformationButton.click();
        return new MyPersonalInformationPage(driver);
    }

    public MyAddressesPage openMyAddresses() {
        LoggerSingleton.getInstance().log("Opening My Addresses page");
        myAddressesButton.click();
        return new MyAddressesPage(driver);
    }

}
