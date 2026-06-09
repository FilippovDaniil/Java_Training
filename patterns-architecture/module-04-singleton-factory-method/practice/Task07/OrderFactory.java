// Фабрика собирает готовый Order, проставляя номер из Singleton-генератора.
class OrderFactory {
    public Order create(String sku, long priceCents) {
        // TODO: получить номер у OrderNumberGenerator.getInstance().next()
        //       и вернуть new Order(номер, sku, priceCents)
        return null;
    }
}
