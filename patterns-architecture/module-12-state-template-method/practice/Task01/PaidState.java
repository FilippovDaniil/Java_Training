class PaidState implements OrderState {
    @Override
    public OrderState pay() {
        // TODO: уже оплачен — остаётся this
        return this;
    }

    @Override
    public OrderState ship() {
        // TODO: перейти в new ShippedState()
        return this;
    }

    @Override
    public String name() {
        // TODO: "PAID"
        return "";
    }
}
