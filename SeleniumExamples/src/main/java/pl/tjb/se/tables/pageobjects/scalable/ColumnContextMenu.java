package pl.tjb.se.tables.pageobjects.scalable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.tjb.se.tables.pageobjects.BasePage;

import java.time.Duration;

public class ColumnContextMenu extends BasePage {
    private final static String ROOT_ELEMENT_XPATH = "//div[@role='tooltip']";
    private final static By ROOT_ELEMENT_BY = By.xpath(ROOT_ELEMENT_XPATH);
    private final static By ASC_BUTTON_RELATIVE_BY = By.xpath(".//li[@data-value='asc']");
    private final static By DESC_BUTTON_RELATIVE_BY = By.xpath(".//li[@data-value='desc']");
    private final static By HIDE_BUTTON_RELATIVE_BY = By.xpath(".//li//span[contains(text(), 'Hide')]/../..");

    @FindBy(xpath = ROOT_ELEMENT_XPATH)
    private WebElement rootElement;

    public ColumnContextMenu(WebDriver driver) {
        super(driver);
    }

    public void sortAscending(){
        rootElement.findElement(ASC_BUTTON_RELATIVE_BY).click();
        waitForContextMenuToDisappear();
    }

    public void sortDescending(){
        rootElement.findElement(DESC_BUTTON_RELATIVE_BY).click();
        waitForContextMenuToDisappear();
    }

    public void hideColumn() {
        rootElement.findElement(HIDE_BUTTON_RELATIVE_BY).click();
        waitForContextMenuToDisappear();
    }

    @Override
    public boolean isLoaded() {
        WebElement rootElement = driver.findElement(ROOT_ELEMENT_BY);
        if (rootElement.isDisplayed()){
            return rootElement.findElement(ASC_BUTTON_RELATIVE_BY).isEnabled() ||
                    rootElement.findElement(DESC_BUTTON_RELATIVE_BY).isEnabled();
        }
        return false;
    }

    private void waitForContextMenuToDisappear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ROOT_ELEMENT_BY));
    }
}
