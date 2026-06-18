package m30_design_patterns.practice.task07;

class Latte implements Drink {
    @Override
    public String name() {
        return "Латте";
    }

    @Override
    public double cost() {
        return 150.0;
    }
}