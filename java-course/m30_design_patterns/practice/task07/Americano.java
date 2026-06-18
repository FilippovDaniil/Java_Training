package m30_design_patterns.practice.task07;

class Americano implements Drink {
    @Override
    public String name() {
        return "Американо";
    }

    @Override
    public double cost() {
        return 130.0;
    }
}