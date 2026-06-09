abstract class Beverage {
    // ШАБЛОННЫЙ МЕТОД: фиксированный рецепт.
    public final void prepare() {
        // TODO: напечатать "вода вскипячена";
        //       напечатать brew();
        //       напечатать "налито в чашку";
        //       если wantsCondiments() — напечатать addCondiments()
    }

    protected abstract String brew();
    protected abstract String addCondiments();

    // hook: по умолчанию добавки нужны
    protected boolean wantsCondiments() {
        return true;
    }
}
