package m17_hexagonal_ports_adapters.practice.task04;

class DiscountedPriceProvider implements PriceProvider {
    @Override
    public long priceOf(String sku) {
        // TODO: sku.length() * 50L (вдвое дешевле static)
        return 0;
    }
}
