package m04_singleton_factory_method.practice.task07;

// Фабрика собирает готовый Order, проставляя номер из Singleton-генератора.
class OrderFactory {
    public Order create(String sku, long priceCents) {
        // TODO: получить номер у OrderNumberGenerator.getInstance().next()
        //       и вернуть new Order(номер, sku, priceCents)
        return null;
    }
}
