class NoDiscount implements DiscountStrategy {
    @Override
    public long apply(long priceCents) {
        // TODO: без изменений
        return priceCents;
    }
}
