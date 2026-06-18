package m30_design_patterns.practice.task07;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price;
    }

    @Override
    public String toString() {
        return "Без скидки";
    }
}
