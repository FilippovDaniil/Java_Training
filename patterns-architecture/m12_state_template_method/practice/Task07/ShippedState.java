package m12_state_template_method.practice.task07;

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
