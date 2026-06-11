package m02_solid_srp_ocp.practice.task04;

// Новая политика. Добавляется без правок PriceCalculator и других политик (OCP).
class ThresholdDiscount implements DiscountPolicy {

    // TODO: поля thresholdCents, discountCents + конструктор

    @Override
    public long apply(long priceCents) {
        // TODO: если priceCents >= threshold, вычесть discountCents (не ниже 0)
        return priceCents;
    }
}
