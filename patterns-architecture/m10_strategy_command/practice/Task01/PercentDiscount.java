package m10_strategy_command.practice.task01;

class PercentDiscount implements DiscountStrategy {
    // TODO: поле percent (0..100) + конструктор PercentDiscount(int percent)

    @Override
    public long apply(long priceCents) {
        // TODO: priceCents - priceCents * percent / 100 (не ниже 0)
        return priceCents;
    }
}
