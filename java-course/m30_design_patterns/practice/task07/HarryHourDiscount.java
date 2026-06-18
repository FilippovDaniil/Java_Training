package m30_design_patterns.practice.task07;

class HappyHourDiscount implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price * 0.8; // 20% скидка
    }

    @Override
    public String toString() {
        return "Счастливый час (20%)";
    }
}