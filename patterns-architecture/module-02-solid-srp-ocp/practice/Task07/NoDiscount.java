class NoDiscount implements DiscountPolicy {
    @Override
    public long apply(long amountCents) {
        // TODO: без изменений
        return amountCents;
    }
}
