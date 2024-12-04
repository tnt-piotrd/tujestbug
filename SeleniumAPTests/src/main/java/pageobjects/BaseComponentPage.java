package pageobjects;

import org.openqa.selenium.WebElement;

public class BaseComponentPage {
    protected WebElement rootElement;

    public BaseComponentPage(WebElement rootElement) {
        this.rootElement = rootElement;
    }
}
