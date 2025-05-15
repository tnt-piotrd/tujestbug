package pl.tjb.se.test.tables;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tjb.se.tables.pageobjects.scalable.DataTable;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DataTableTest extends BaseTest{
    private DataTable dataTable;

    @BeforeMethod
    private void assignDataTable(){
        dataTable = reactTablesPage.getDataTable();
    }

    @Test
    public void shouldGetColumnNames(){
        List<String> columnNames = dataTable.getColumnNames();
        assertThat(columnNames, hasItem("Age"));
        assertThat(columnNames, hasSize(5));
    }

    @Test
    public void shouldHaveJonSnowInFirstRow(){
        Map<String, String> jonRecord = dataTable.getRowDataWithFollowingColumnValue("Last name", "Snow");
        assertThat(jonRecord, hasEntry("First name", "Jon"));
    }

    @Test
    public void defaultSortingShouldBeIdAscending(){
        Map<String, String> firstRow = dataTable.getXthRowData(0);
        assertThat(firstRow, hasEntry("ID", "1"));
    }

    @Test
    public void userShouldBeAbleToSortByIdDesc(){
        dataTable.sortBy("ID", false);
        Map<String, String> firstRow = dataTable.getXthRowData(0);
        assertThat(firstRow, hasEntry("ID", "9"));
    }

    @Test
    public void userShouldBeAbleToSortByLastNameAsc(){
        dataTable.sortBy("Last name", true);
        Map<String, String> firstRow = dataTable.getXthRowData(0);
        assertThat(firstRow, hasEntry("Last name", "Clifford"));
    }

    @Test
    public void userShouldBeAbleToHideColumn() {
        dataTable.hideColumn("Age");
        List<String> headers = dataTable.getColumnNames();
        assertThat(headers, not(hasItem("Age")));
        assertThat(headers, hasSize(4));
    }

    @Test
    public void userShouldBeAbleToSelectMultipleRows() {
        dataTable.selectRows(List.of(1, 2, 3));
        assertThat(dataTable.getRowsSelectedInfo(), equalTo("3 rows selected"));
    }

    @Test
    public void userShouldBeAbleToSelectRowWithCriteria() {
        dataTable.selectRow("Full name", "Jaime Lannister");
        assertThat(dataTable.getRowsSelectedInfo(), equalTo("1 row selected"));
    }
}
