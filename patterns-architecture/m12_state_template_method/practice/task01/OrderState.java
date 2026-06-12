package m12_state_template_method.practice.task01;

interface OrderState {
    OrderState pay();
    OrderState ship();
    String name();
}
