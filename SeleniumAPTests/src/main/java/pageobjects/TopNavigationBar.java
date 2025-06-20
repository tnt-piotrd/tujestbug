package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import other.LoggerSingleton;

import static utils.TimeOuts._30_SECONDS;

public class TopNavigationBar extends BasePage {
    private static final String SEARCH_INPUT_ID = "search_query_top";

    @FindBy(className = "login")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement userDetailsButton;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartButton;

    @FindBy(className = "logout")
    private WebElement signOutButton;

    @FindBy(id = SEARCH_INPUT_ID)
    private WebElement searchInput;

    public TopNavigationBar(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPage() {
        signInButton.click();
        return new LoginPage(driver);
    }

    public String getUserName() {
        return userDetailsButton.getText();
    }

    public String getCartStatus() {
        return cartButton.getText();
    }

    public void logout() {
        signOutButton.click();
    }

    public SearchResultsPage search(String phrase) {
        searchInput.sendKeys(phrase);
        searchInput.submit();
        return new SearchResultsPage(driver);
    }

    public MyAccountPage openUserAccountDetails() {
        LoggerSingleton.getInstance().log("Opening user account details");
        userDetailsButton.click();
        return new MyAccountPage(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.elementToBeClickable(By.id(SEARCH_INPUT_ID)));
    }
}
