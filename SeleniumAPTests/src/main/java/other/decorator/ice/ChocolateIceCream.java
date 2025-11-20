package other.decorator.ice;

public class ChocolateIceCream extends IceCream{
    @Override
    public String getDescription() {
        return "Chocolate Ice Cream";
    }

    @Override
    public double getCost() {
        return 5.50;
    }
}