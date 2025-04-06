package pl.tjb.se.tables.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private final WebDriver driver;
    private final By tableLocator;

    private final static By COLUMN_HEADERS_LOCATOR = By.xpath(".//thead/tr/th");
    private final static By ROWS_LOCATOR = By.xpath(".//tbody/tr");
    private final static By CELLS_IN_ROW_LOCATOR = By.xpath(".//*");

    public Table(WebDriver driver, By tableLocator) {
        this.driver = driver;
        this.tableLocator = tableLocator;
    }

    public List<String> getColumnNames() {
        return driver.findElement(tableLocator).findElements(COLUMN_HEADERS_LOCATOR).stream()
                .map(WebElement::getText).toList();
    }

    public Map<String, String> getXthRowData(int rowIndex) {
        Map<String, String> rowData = new HashMap<>();
        List<String> columnNames = getColumnNames();
        WebElement row = getAllRows().get(rowIndex);
        List<WebElement> cellsInRow = getCellsInRow(row);

        for (int i=0; i<columnNames.size(); i++){
            rowData.put(columnNames.get(i), cellsInRow.get(i).getText());
        }
        return rowData;
    }

    public List<String> getAllCellsInColumn(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        List<String> columnCells = new ArrayList<>();
        for (WebElement row:getAllRows()){
            columnCells.add(getCellsInRow(row).get(columnIndex).getText());
        }
        return columnCells;
    }

    public Map<String, String> getRowDataWithFollowingColumnValue(String columnName, String columnValue) {
        List<String> allValuesInColumn = getAllCellsInColumn(columnName);
        int rowIndex = allValuesInColumn.indexOf(columnValue);
        if (rowIndex<0 || allValuesInColumn.indexOf(columnValue)!=allValuesInColumn.lastIndexOf(columnValue)){
            throw new RuntimeException("There was no row with value '%s' in column name '%s' or value was not unique");
        }
        return getXthRowData(rowIndex);
    }

    private List<WebElement> getAllRows(){
        return driver.findElement(tableLocator).findElements(ROWS_LOCATOR);
    }

    private List<WebElement> getCellsInRow(WebElement row){
        return row.findElements(CELLS_IN_ROW_LOCATOR);
    }

    private int getColumnIndex(String columnName){
        List<String> columnNames = getColumnNames();
        int index = columnNames.indexOf(columnName);
        if (index<0){
            throw new RuntimeException("Unable to find column with name %s".formatted(columnName));
        }
        return index;
    }
}
