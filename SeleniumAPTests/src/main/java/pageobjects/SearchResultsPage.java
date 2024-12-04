package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static utils.TimeOuts._30_SECONDS;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = NAVIGATION_BUTTON_XPATH)
    private WebElement navigationButton;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> productsList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration())
                .until(ExpectedConditions.textMatches(By.xpath(NAVIGATION_BUTTON_XPATH), Pattern.compile(".*Search.*")));
    }

    public List<ProductComponent> getAllDisplayedProducts() {
        return productsList.stream()
                .map(e -> new ProductComponent(e))
                .toList();
    }

    public ProductComponent getDisplayedProduct(Predicate<ProductComponent> condition) {
        return getAllDisplayedProducts()
                .stream()
                .filter(condition)
                .findFirst()
                .orElseThrow();
    }
}
