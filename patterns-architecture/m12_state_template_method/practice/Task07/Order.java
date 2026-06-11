package m12_state_template_method.practice.task07;

class Order {
    private OrderState state = new NewState();

    public void pay() {
        // TODO: state = state.pay()
    }

    public void ship() {
        // TODO: state = state.ship()
    }

    public String status() {
        // TODO: state.name()
        return "";
    }
}
