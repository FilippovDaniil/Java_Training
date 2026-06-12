package m02_solid_srp_ocp.practice.task04;

class NoDiscount implements DiscountPolicy {
    @Override
    public long apply(long priceCents) {
        // TODO: вернуть цену без изменений
        return priceCents;
    }
}
