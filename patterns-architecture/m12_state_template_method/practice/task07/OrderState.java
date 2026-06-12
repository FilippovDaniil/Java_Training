package m12_state_template_method.practice.task07;

interface OrderState {
    OrderState pay();
    OrderState ship();
    String name();
}
