package m30_design_patterns.practice.task07;

class Espresso implements Drink {
    @Override
    public String name() {
        return "Эспрессо";
    }

    @Override
    public double cost() {
        return 120.0;
    }
}