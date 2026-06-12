package m10_strategy_command.practice.task07;

class NoDiscount implements DiscountStrategy {
    @Override
    public long apply(long sumCents) {
        // TODO: без изменений
        return sumCents;
    }
}
