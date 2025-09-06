package test.other.decorator;

import org.testng.annotations.Test;
import other.decorator.ice.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class IceCreamDecoratorTest {
    @Test
    public void shouldCreateVanillaIceCream() {
        IceCream iceCream = new VanillaIceCream();
        assertEquals(iceCream.getDescription(), "Vanilla Ice Cream");
        assertEquals(iceCream.getCost(), 5.00);
    }

    @Test
    public void shouldDecorateWithWhippedCream() {
        IceCream iceCream = new VanillaIceCream();
        iceCream = new WhippedCream(iceCream);

        assertEquals(iceCream.getDescription(), "Vanilla Ice Cream, Whipped Cream");
        assertEquals(iceCream.getCost(), 8.00); // 5.00 + 3.00
    }

    @Test
    public void shouldDecorateWithMultipleToppings() {
        IceCream iceCream = new ChocolateIceCream();
        iceCream = new WhippedCream(iceCream);
        iceCream = new Strawberries(iceCream);
        iceCream = new ChocolateChunks(iceCream);
        iceCream = new ChocolateSauce(iceCream);

        assertEquals(iceCream.getDescription(),
                "Chocolate Ice Cream, Whipped Cream, Strawberries," +
                        " Chocolate Chunks, Chocolate Sauce");
        assertEquals(iceCream.getCost(), 19.00); //5.50 + 3.00 + 4.00 + 4.00 + 2.50
    }

    @Test
    public void shouldAllowDifferentOrderOfDecorators() {
        IceCream iceCream1 = new ChocolateSauce(
                new Strawberries(
                        new WhippedCream(
                                new VanillaIceCream())));
        IceCream iceCream2 = new WhippedCream(
                new Strawberries(
                        new ChocolateSauce(
                                new VanillaIceCream())));
        assertNotEquals(iceCream1.getDescription(), iceCream2.getDescription());
        assertEquals(iceCream1.getCost(), iceCream2.getCost());
    }
}
