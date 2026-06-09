interface ShippingMethod {
    String name();
    long costCents(int weightGrams);
}
