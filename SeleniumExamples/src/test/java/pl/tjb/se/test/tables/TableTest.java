package pl.tjb.se.test.tables;

import org.testng.annotations.Test;
import pl.tjb.se.tables.pageobjects.Table;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class TableTest extends BaseTest{
    private static final String FIRST_BASIC_TABLE_COLUMN_NAME = "Dessert (100g serving)";

    @Test
    public void shouldReturnColumnNames(){
        List<String> columnNames = reactTablesPage.getTable().getColumnNames();
        assertThat(columnNames, hasItem("Calories"));
        assertThat(columnNames, hasSize(5));
    }

    @Test
    public void firstDesertShouldBeFrozenYoghurt(){
        Table table = reactTablesPage.getTable();
        Map<String, String> firstRowData = table.getXthRowData(0);
        assertThat(firstRowData, hasEntry(FIRST_BASIC_TABLE_COLUMN_NAME, "Frozen yoghurt"));
    }

    @Test
    public void shouldGetDessertNames(){
        List<String> desertNames = reactTablesPage.getTable().getAllCellsInColumn(FIRST_BASIC_TABLE_COLUMN_NAME);
        assertEquals(desertNames.get(4), "Gingerbread");
        assertEquals(desertNames.size(), 5);
    }

    @Test
    public void eclairCaloriesShouldBe262(){
        Map<String, String> eclairData = reactTablesPage.getTable()
                .getRowDataWithFollowingColumnValue(FIRST_BASIC_TABLE_COLUMN_NAME, "Eclair");
        assertThat(eclairData, hasEntry("Calories", "262"));
    }
}
