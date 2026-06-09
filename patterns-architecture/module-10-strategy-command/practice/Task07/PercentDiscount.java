class PercentDiscount implements DiscountStrategy {
    // TODO: поле percent (0..100) + конструктор PercentDiscount(int percent)

    @Override
    public long apply(long sumCents) {
        // TODO: sumCents - sumCents * percent / 100 (не ниже 0)
        return sumCents;
    }
}
