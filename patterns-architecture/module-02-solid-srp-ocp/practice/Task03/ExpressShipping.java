class ExpressShipping implements ShippingMethod {
    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public long costCents(int weightGrams) {
        // TODO: 12 коп. за каждые начатые 100 г
        return 0;
    }
}
