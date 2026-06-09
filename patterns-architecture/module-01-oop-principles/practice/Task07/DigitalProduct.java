class DigitalProduct extends Product {

    // TODO: конструктор DigitalProduct(String id, String title, long priceCents)

    @Override
    public long shippingCostCents() {
        // TODO: цифровой товар не доставляется физически → 0
        return 0;
    }
}
