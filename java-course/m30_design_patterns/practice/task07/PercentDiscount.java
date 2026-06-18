package m30_design_patterns.practice.task07;

class PercentDiscount implements DiscountStrategy {
    private double percent;

    public PercentDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        return price * (1 - percent / 100);
    }

    @Override
    public String toString() {
        return "Скидка " + percent + "%";
    }
}