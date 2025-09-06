package other.decorator.ice;

public class ChocolateChunks extends IceCreamDecorator{
    public ChocolateChunks(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + ", Chocolate Chunks";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 4.00;
    }
}
