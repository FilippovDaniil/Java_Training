class NoDiscount implements DiscountStrategy {
    @Override
    public long apply(long sumCents) {
        // TODO: без изменений
        return sumCents;
    }
}
