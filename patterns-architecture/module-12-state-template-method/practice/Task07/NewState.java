class NewState implements OrderState {
    @Override
    public OrderState pay() {
        // TODO: new PaidState()
        return this;
    }

    @Override
    public OrderState ship() {
        // TODO: нельзя — остаётся this
        return this;
    }

    @Override
    public String name() {
        // TODO: "NEW"
        return "";
    }
}
