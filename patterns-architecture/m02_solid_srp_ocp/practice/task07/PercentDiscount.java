package m02_solid_srp_ocp.practice.task07;

class PercentDiscount implements DiscountPolicy {
    // TODO: поле percent (0..100) + конструктор

    @Override
    public long apply(long amountCents) {
        // TODO: минус percent% (не ниже 0)
        return amountCents;
    }
}
