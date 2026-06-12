package m07_adapter_decorator.practice.task03;

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee inner) {
        super(inner);
    }

    @Override
    public long costCents() {
        // TODO: inner.costCents() + 500
        return 0;
    }

    @Override
    public String description() {
        // TODO: inner.description() + " + сахар"
        return "";
    }
}
