package test.automationpractice;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;

public class CartTest extends BaseTest {
    @Test()
    public void cartShouldBeEmptyWhenNewSessionBegins() {
        String cartStatus = new TopNavigationBar(driver).getCartStatus();
        Assert.assertTrue(cartStatus.contains("(empty)"), "Cart was not empty !");
    }
}
