package pl.tjb.se.test.tables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.tjb.se.tables.pageobjects.ReactTablesPage;

public class BaseTest {
    protected WebDriver driver;
    protected ReactTablesPage reactTablesPage;

    @BeforeMethod(alwaysRun = true)
    public void openReactTables() {
        driver = new ChromeDriver();
        driver.get("https://mui.com/material-ui/react-table/");
        reactTablesPage = new ReactTablesPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
