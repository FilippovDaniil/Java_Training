package m30_design_patterns.practice.task07;

public class SyrupDecorator extends DrinkDecorator{
    private String syrupType;

    public SyrupDecorator(Drink drink, String syrupType) {
        super(drink);
        this.syrupType = syrupType;
    }

    @Override
    public String name() {
        return drink.name() + " + сироп (" + syrupType + ")";
    }

    @Override
    public double cost() {
        return drink.cost() + 40.0;
    }
}
