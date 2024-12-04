package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductComponent extends BaseComponentPage {
    private static final By PRODUCT_NAME_BY = By.xpath(".//a[@class='product-name']");
    private static final By CURRENT_PRICE_BY = By.xpath(".//div[@class='right-block']//span[@class='price product-price']");

    public ProductComponent(WebElement rootElement) {
        super(rootElement);
    }

    public String getName() {
        return rootElement.findElement(PRODUCT_NAME_BY).getText();
    }

    public String getCurrentPrice() {
        return rootElement.findElement(CURRENT_PRICE_BY)
                .getText();
    }
}
