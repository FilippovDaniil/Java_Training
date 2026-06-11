package m12_state_template_method.practice.task01;

// Контекст: делегирует поведение текущему состоянию (без switch).
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
