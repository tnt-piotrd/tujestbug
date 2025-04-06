package pl.tjb.se.test.tables;

import org.testng.annotations.Test;
import pl.tjb.se.tables.pageobjects.ReactTablesPage;
import pl.tjb.se.tables.pageobjects.scalable.BasicTable;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class BasicTableTest extends BaseTest{

    private static final String NAME_OF_DESSERT_COLUMN = "Dessert (100g serving)";

    @Test
    public void shouldReturnColumnNames(){
        BasicTable basicTable = new ReactTablesPage(driver).getBasicTable();
        List<String> columnNames = basicTable.getColumnNames();
        assertThat(columnNames, hasItem("Calories"));
        assertThat(columnNames, hasSize(5));
    }

    @Test
    public void firstDesertShouldBeFrozenYoghurt(){
        BasicTable basicTable = new ReactTablesPage(driver).getBasicTable();
        Map<String, String> firstRowData = basicTable.getXthRowData(0);
        assertThat(firstRowData, hasEntry(NAME_OF_DESSERT_COLUMN, "Frozen yoghurt"));
    }

    @Test
    public void shouldGetDessertNames(){
        List<String> desertNames = new ReactTablesPage(driver).getBasicTable().getAllCellsInColumn(NAME_OF_DESSERT_COLUMN);
        assertThat(desertNames, hasItem("Eclair"));
        assertThat(desertNames, hasSize(5));
    }

    @Test
    public void eclairCaloriesShouldBe262(){
        BasicTable basicTable = new ReactTablesPage(driver).getBasicTable();
        Map<String, String> eclairData = basicTable
                .getRowDataWithFollowingColumnValue(NAME_OF_DESSERT_COLUMN, "Eclair");
        assertEquals(eclairData.get("Calories"), "262");
    }
}
