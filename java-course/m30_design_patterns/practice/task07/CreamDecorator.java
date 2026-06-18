package m30_design_patterns.practice.task07;

class CreamDecorator extends DrinkDecorator {
    public CreamDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String name() {
        return drink.name() + " + сливки";
    }

    @Override
    public double cost() {
        return drink.cost() + 35.0;
    }
}