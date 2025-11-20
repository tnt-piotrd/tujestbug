package other.decorator.ice;

public class WhippedCream extends IceCreamDecorator{
    public WhippedCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 3.00;
    }
}
