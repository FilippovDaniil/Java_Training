package m30_design_patterns.practice.task07;

class ChocolateDecorator extends DrinkDecorator {
    public ChocolateDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String name() {
        return drink.name() + " + шоколад";
    }

    @Override
    public double cost() {
        return drink.cost() + 45.0;
    }
}