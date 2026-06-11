package m02_solid_srp_ocp.practice.task04;

class PercentDiscount implements DiscountPolicy {

    // TODO: поле percent (0..100) + конструктор PercentDiscount(int percent)

    @Override
    public long apply(long priceCents) {
        // TODO: вернуть цену минус percent% (не ниже 0)
        return priceCents;
    }
}
