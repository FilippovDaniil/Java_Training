package m30_design_patterns.practice.task07;

class Cappuccino implements Drink {
    @Override
    public String name() {
        return "Капучино";
    }

    @Override
    public double cost() {
        return 160.0;
    }
}
