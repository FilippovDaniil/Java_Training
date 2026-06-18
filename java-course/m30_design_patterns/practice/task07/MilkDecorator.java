package m30_design_patterns.practice.task07;

public class MilkDecorator extends DrinkDecorator{

    public MilkDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String name() {
        return drink.name() + " + молоко";
    }

    @Override
    public double cost() {
        return drink.cost() + 30.0;
    }
}
