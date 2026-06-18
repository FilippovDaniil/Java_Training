package m30_design_patterns.practice.task07;

public abstract class DrinkDecorator implements Drink{
    protected Drink drink;

    public DrinkDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String name() {
        return drink.name();
    }

    @Override
    public double cost() {
        return drink.cost();
    }
}
