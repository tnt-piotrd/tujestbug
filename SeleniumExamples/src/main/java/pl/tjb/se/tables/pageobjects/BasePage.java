package pl.tjb.se.tables.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitForPageToBeLoaded();
        initializePageFactory();
    }

    /**
     * Should not base on page objects
     * as it's called before page objects initialization
     *
     * @return true when the page is loaded
     */
    public abstract boolean isLoaded();

    private void initializePageFactory() {
        PageFactory.initElements(driver, this);
    }

    protected void waitForPageToBeLoaded() {
        long timeOutInSeconds = 15;
        try {
            final FluentWait<BasePage> wait = new FluentWait<>(this)
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(BasePage::isLoaded);
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Timed out after %d seconds on loading page %s",
                    timeOutInSeconds, this.getClass().getName()), e);
        }
    }
}