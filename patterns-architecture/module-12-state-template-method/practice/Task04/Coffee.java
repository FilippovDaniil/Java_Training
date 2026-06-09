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
