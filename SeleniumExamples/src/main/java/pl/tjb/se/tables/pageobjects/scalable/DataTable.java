package pl.tjb.se.tables.pageobjects.scalable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DataTable extends AbstractTable {
    private static final String OPEN_CONTEXT_MENU_RELATIVE_XPATH = ".//button[@aria-haspopup='menu']";
    private static final String ROW_SELECTION_SUMMARY_RELATIVE_XPATH =".//div[contains(@class, 'selectedRowCount')]";

    public DataTable(WebDriver driver, By tableLocator) {
        super(driver, tableLocator);
    }

    @Override
    protected List<WebElement> getHeaders() {
        return getRootElement().findElements(By.xpath(".//div[@role='columnheader']" +
                "//div[contains(@class, 'columnHeaderTitle css')]"));
    }

    @Override
    protected List<WebElement> getRows() {
        return getRootElement().findElements(By.xpath(".//div[@role='row'][not(contains(@class, 'border'))]"));
    }

    @Override
    protected List<WebElement> getCellsInRow(WebElement row) {
        return row.findElements(By.xpath(".//div[@role='gridcell'][not(@aria-colindex='1')]"));
    }

    public void sortBy(String columnName, boolean isAscending) {
        ColumnContextMenu contextMenu = openContextMenuInColumn(columnName);
        if (isAscending) {
            contextMenu.sortAscending();
        } else {
            contextMenu.sortDescending();
        }
    }

    public void hideColumn(String columnName) {
        ColumnContextMenu contextMenu = openContextMenuInColumn(columnName);
        contextMenu.hideColumn();
    }

    private ColumnContextMenu openContextMenuInColumn(String columnName) {
        WebElement header = getHeaderField(columnName);
        new Actions(driver).scrollToElement(header).moveToElement(header).pause(1000).build().perform();
        header.findElement(By.xpath(OPEN_CONTEXT_MENU_RELATIVE_XPATH)).click();
        return new ColumnContextMenu(driver);
    }

    private WebElement getHeaderField(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        return getHeaders().get(columnIndex).findElement(By.xpath("./../../.."));
    }

    public void selectRows(List<Integer> rowIndexes) {
        for (Integer indexToBeSelected:rowIndexes){
            getRows().get(indexToBeSelected).findElement(By.xpath(".//input")).click();
        }
    }

    public void selectRow(String columnName, String columnValue) {
        int rowIndex = getRowNumberHavingValueInColumn(columnName, columnValue);
        selectRows(List.of(rowIndex));
    }

    public String getRowsSelectedInfo() {
        return getRootElement().findElement(By.xpath(ROW_SELECTION_SUMMARY_RELATIVE_XPATH)).getText();
    }
}
