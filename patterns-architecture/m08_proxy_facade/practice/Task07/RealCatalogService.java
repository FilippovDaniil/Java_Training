package m08_proxy_facade.practice.task07;

class RealCatalogService implements CatalogService {
    @Override
    public long priceOf(String sku) {
        // TODO: напечатать "запрос цены: " + sku и вернуть sku.length() * 1000L
        return 0;
    }
}
