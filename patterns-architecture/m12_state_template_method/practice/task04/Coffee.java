package m12_state_template_method.practice.task04;

class Coffee extends Beverage {
    @Override
    protected String brew() {
        // TODO: "заварен кофе"
        return "";
    }

    @Override
    protected String addCondiments() {
        // TODO: "добавлено молоко"
        return "";
    }
    // wantsCondiments не переопределяем — добавки нужны
}
