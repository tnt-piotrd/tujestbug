package pl.tjb.se.tables.pageobjects.scalable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasicTable extends AbstractTable{
    public BasicTable(WebDriver driver, By tableLocator) {
        super(driver, tableLocator);
    }

    @Override
    protected List<WebElement> getHeaders() {
        return getRootElement().findElements(By.xpath(".//thead/tr/th"));
    }

    @Override
    protected List<WebElement> getRows() {
        return getRootElement().findElements(By.xpath(".//tbody/tr"));
    }

    @Override
    protected List<WebElement> getCellsInRow(WebElement row) {
        return row.findElements(By.xpath(".//*"));
    }
}
