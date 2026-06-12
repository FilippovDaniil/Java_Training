package m08_proxy_facade.practice.task03;

class RealPriceService implements PriceService {
    @Override
    public long priceOf(String sku) {
        // TODO: напечатать "запрос к БД: " + sku и вернуть sku.length() * 1000L
        return 0;
    }
}
