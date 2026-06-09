class NoDiscount implements DiscountPolicy {
    @Override
    public long apply(long priceCents) {
        // TODO: вернуть цену без изменений
        return priceCents;
    }
}
