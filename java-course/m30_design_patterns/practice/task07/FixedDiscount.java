package m30_design_patterns.practice.task07;

class FixedDiscount implements DiscountStrategy {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double price) {
        return Math.max(0, price - amount);
    }

    @Override
    public String toString() {
        return "Скидка " + amount + " руб";
    }
}