package m12_state_template_method.practice.task04;

class Tea extends Beverage {
    @Override
    protected String brew() {
        // TODO: "заварен чай"
        return "";
    }

    @Override
    protected String addCondiments() {
        // TODO: "добавлен лимон"
        return "";
    }

    @Override
    protected boolean wantsCondiments() {
        // TODO: false — этот чай без добавок
        return false;
    }
}
