class NewState implements OrderState {
    @Override
    public OrderState pay() {
        // TODO: перейти в new PaidState()
        return this;
    }

    @Override
    public OrderState ship() {
        // TODO: нельзя отгрузить неоплаченный — остаётся this
        return this;
    }

    @Override
    public String name() {
        // TODO: "NEW"
        return "";
    }
}
