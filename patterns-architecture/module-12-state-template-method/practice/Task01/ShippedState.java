class ShippedState implements OrderState {
    @Override
    public OrderState pay() {
        // TODO: уже отгружен — остаётся this
        return this;
    }

    @Override
    public OrderState ship() {
        // TODO: уже отгружен — остаётся this
        return this;
    }

    @Override
    public String name() {
        // TODO: "SHIPPED"
        return "";
    }
}
