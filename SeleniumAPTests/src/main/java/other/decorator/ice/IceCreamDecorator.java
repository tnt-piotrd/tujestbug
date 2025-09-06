package other.decorator.ice;

public abstract class IceCreamDecorator extends IceCream{
    protected IceCream iceCream;

    public IceCreamDecorator(IceCream iceCream) {
        this.iceCream = iceCream;
    }
}
