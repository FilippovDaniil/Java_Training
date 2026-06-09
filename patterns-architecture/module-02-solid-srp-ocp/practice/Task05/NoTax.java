class NoTax implements TaxRule {
    @Override
    public long taxCents(long amountCents) {
        // TODO: налог 0
        return 0;
    }
}
