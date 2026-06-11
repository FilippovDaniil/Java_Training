package m02_solid_srp_ocp.practice.task07;

class NoDiscount implements DiscountPolicy {
    @Override
    public long apply(long amountCents) {
        // TODO: без изменений
        return amountCents;
    }
}
