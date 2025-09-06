package other.decorator.ice;

public class ChocolateSauce extends IceCreamDecorator{
    public ChocolateSauce(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + ", Chocolate Sauce";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 2.50;
    }
}
