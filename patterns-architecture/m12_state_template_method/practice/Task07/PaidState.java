package m12_state_template_method.practice.task07;

class PaidState implements OrderState {
    @Override
    public OrderState pay() {
        // TODO: уже оплачен — остаётся this
        return this;
    }

    @Override
    public OrderState ship() {
        // TODO: new ShippedState()
        return this;
    }

    @Override
    public String name() {
        // TODO: "PAID"
        return "";
    }
}
