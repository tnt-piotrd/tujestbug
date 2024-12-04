package test;

import org.testng.annotations.Test;
import pageobjects.ProductComponent;
import pageobjects.TopNavigationBar;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchTest extends BaseTest {

    private static final String CHIFFON_DRESS = "Printed Chiffon Dress";
    private static final String SHORT_SLEEVE = "Faded Short Sleeve T-shirts";

    @Test
    public void invalidPhraseShouldShowNoResults() {
        List<ProductComponent> productComponentList = new TopNavigationBar(driver).search("suit").getAllDisplayedProducts();
        assertEquals(productComponentList.size(), 0);
    }

    @Test
    public void userShouldBeAbleToFindByPartialMatch() {
        ProductComponent chiffonDress = new TopNavigationBar(driver).search("dress")
                .getDisplayedProduct(p -> p.getName().equals(CHIFFON_DRESS));
        assertEquals(chiffonDress.getName(), CHIFFON_DRESS);
        assertEquals(chiffonDress.getCurrentPrice(), "$16");
    }

    @Test
    public void userShouldBeAbleToFindByExactMatch() {
        ProductComponent tShirt = new TopNavigationBar(driver).search(SHORT_SLEEVE)
                .getDisplayedProduct(p -> p.getName().equals(SHORT_SLEEVE));
        assertEquals(tShirt.getName(), SHORT_SLEEVE);
        assertEquals(tShirt.getCurrentPrice(), "$17");
    }

}
