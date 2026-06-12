package m10_strategy_command.practice.task01;

class NoDiscount implements DiscountStrategy {
    @Override
    public long apply(long priceCents) {
        // TODO: без изменений
        return priceCents;
    }
}
