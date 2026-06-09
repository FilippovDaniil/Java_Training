class ShippedState implements OrderState {
    @Override
    public OrderState pay() {
        return this;
    }

    @Override
    public OrderState ship() {
        return this;
    }

    @Override
    public String name() {
        // TODO: "SHIPPED"
        return "";
    }
}
