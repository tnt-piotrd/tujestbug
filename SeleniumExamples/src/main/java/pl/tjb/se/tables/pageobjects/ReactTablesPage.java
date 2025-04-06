package pl.tjb.se.tables.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReactTablesPage extends BasePage{
    private static final By SEARCH_BUTTON_LOCATOR =
            By.xpath("//button[@aria-labelledby='app-search-label']");
    private static final By BASIC_TABLE_LOCATOR_BY = By.xpath("//div[@id='BasicTable']/..//table");

    @FindBy(xpath = "//div[@id='BasicTable']/..//table/tbody/tr[1]/th")
    private WebElement basicTableFirstDessertName;

    @FindBy(xpath = "//div[@id='BasicTable']/..//table/tbody/tr/th")
    private List<WebElement> basicTableAllDessertsRows;

    @FindBy(xpath = "//div[@id='BasicTable']/..//table/tbody/tr[3]/*[2]")
    private WebElement basicTableEclairCalories;

    public ReactTablesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return driver.findElement(SEARCH_BUTTON_LOCATOR).isDisplayed();
    }

    public String getFirstDessertNameFromBasicTable(){
        return basicTableFirstDessertName.getText();
    }

    public List<String> getAllDesertsInBasicTable(){
        return basicTableAllDessertsRows.stream().map(WebElement::getText).toList();
    }

    public String getEclairCaloriesFromBasicTable(){
        return basicTableEclairCalories.getText();
    }

    public Table getTable() {
        return new Table(driver, BASIC_TABLE_LOCATOR_BY);
    }
}
