class Milk extends CoffeeDecorator {
    public Milk(Coffee inner) {
        super(inner);
    }

    @Override
    public long costCents() {
        // TODO: inner.costCents() + 2000
        return 0;
    }

    @Override
    public String description() {
        // TODO: inner.description() + " + молоко"
        return "";
    }
}
