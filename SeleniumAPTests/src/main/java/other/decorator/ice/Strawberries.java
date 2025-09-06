package other.decorator.ice;

public class Strawberries extends IceCreamDecorator{
    public Strawberries(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + ", Strawberries";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 4.00;
    }
}
