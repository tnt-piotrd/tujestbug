package pl.tjb.se.test.tables;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BasicTableWrongTest extends BaseTest{
    @Test
    public void firstDesertShouldBeFrozenYoghurt(){
        String actualName = reactTablesPage.getFirstDessertNameFromBasicTable();
        assertEquals(actualName, "Frozen yoghurt");
    }

    @Test
    public void shouldGetDessertNames(){
        List<String> desertNames = reactTablesPage.getAllDesertsInBasicTable();
        assertEquals(desertNames.get(4), "Gingerbread");
        assertEquals(desertNames.size(), 5);
    }

    @Test
    public void eclairCaloriesShouldBe262(){
        assertEquals(reactTablesPage.getEclairCaloriesFromBasicTable(), "262");
    }
}
