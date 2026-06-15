package m19_io_nio.practice.task06;

public class SugarDecorator implements Coffee{

    private final SimpleCoffee simpleCoffee;

    public SugarDecorator(SimpleCoffee simpleCoffee) {
        this.simpleCoffee = simpleCoffee;
    }

    @Override
    public String description() {
        return simpleCoffee.description() + " + sugar";
    }

    @Override
    public double cost() {
        return simpleCoffee.cost() + 10;
    }
}
