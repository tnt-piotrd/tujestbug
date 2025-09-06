package other.decorator.ice;

public class VanillaIceCream extends IceCream{
    @Override
    public String getDescription() {
        return "Vanilla Ice Cream";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}
