package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected static final String NAVIGATION_BUTTON_XPATH = "//div[@class='breadcrumb clearfix']";
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitForPageToBeLoaded();
        PageFactory.initElements(driver, this);
    }

    public abstract void waitForPageToBeLoaded();

}
