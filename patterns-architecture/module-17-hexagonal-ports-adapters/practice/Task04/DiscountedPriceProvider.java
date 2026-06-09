class DiscountedPriceProvider implements PriceProvider {
    @Override
    public long priceOf(String sku) {
        // TODO: sku.length() * 50L (вдвое дешевле static)
        return 0;
    }
}
