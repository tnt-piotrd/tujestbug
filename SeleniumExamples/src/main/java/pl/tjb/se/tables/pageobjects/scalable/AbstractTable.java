package pl.tjb.se.tables.pageobjects.scalable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTable {
    protected final WebDriver driver;
    private final By tableLocator;

    public AbstractTable(WebDriver driver, By tableLocator) {
        this.driver = driver;
        this.tableLocator = tableLocator;
    }

    protected WebElement getRootElement() {
        return driver.findElement(tableLocator);
    }

    protected abstract List<WebElement> getHeaders();
    protected abstract List<WebElement> getRows();
    protected abstract List<WebElement> getCellsInRow(WebElement row);

    public List<String> getColumnNames(){
        return getHeaders().stream().map(WebElement::getText).toList();
    }

    public Map<String, String> getXthRowData(int rowIndex){
        WebElement xthRow = getRows().get(rowIndex);
        List<String> headers = getColumnNames();
        return getRowData(headers, xthRow);
    }

    public List<String> getAllCellsInColumn(String columnName){
        int columnIndex = getColumnIndex(columnName);
        List<String> columnCells = new ArrayList<>();
        for (WebElement row:getRows()){
            columnCells.add(getCellsInRow(row).get(columnIndex).getText());
        }
        return columnCells;
    }

    public Map<String, String> getRowDataWithFollowingColumnValue(String columnName, String columnValue){
        return getXthRowData(getRowNumberHavingValueInColumn(columnName, columnValue));
    }

    public List<Map<String, String>> getAllRowsData(){
        List<Map<String, String>> allRowsData = new ArrayList<>();
        List<String> headers = getColumnNames();
        for (WebElement row:getRows()){
            allRowsData.add(getRowData(headers, row));
        }
        return allRowsData;
    }

    private int getRowNumberHavingValueInColumn(String columnName, String columnValue){
        List<String> allCellsInColumn = getAllCellsInColumn(columnName);
        int rowNumber = allCellsInColumn.indexOf(columnValue);
        if (rowNumber<0 || rowNumber!=allCellsInColumn.lastIndexOf(columnValue)){
            throw new RuntimeException(("There was no row with column name '%s' and column value '%s' or value was duplicated inside the column"
                    .formatted(columnName, columnValue)));
        }
        return rowNumber;
    }

    protected int getColumnIndex(String columnName) {
        List<String> columnNames = getColumnNames();
        int index = columnNames.indexOf(columnName);
        if (index < 0 || index!=columnNames.lastIndexOf(columnName)) {
            throw new RuntimeException("Unable to find index for column name '%s' or column name is duplicated".formatted(columnName));
        }
        return index;
    }

    private Map<String, String> getRowData(List<String> headers, WebElement row) {
        Map<String, String> rowData = new HashMap<>();
        List<WebElement> cellsInRow = getCellsInRow(row);
        for (int i=0; i<headers.size(); i++){
            rowData.put(headers.get(i), cellsInRow.get(i).getText());
        }
        return rowData;
    }
}
