package m19_io_nio.practice.task06;

public class MilkDecorator implements Coffee{
    private final SimpleCoffee simpleCoffee;

    public MilkDecorator(SimpleCoffee simpleCoffee) {
        this.simpleCoffee = simpleCoffee;
    }

    @Override
    public String description() {
        return simpleCoffee.description() + " + moloko";
    }

    @Override
    public double cost() {
        return simpleCoffee.cost() + 30;
    }
}
