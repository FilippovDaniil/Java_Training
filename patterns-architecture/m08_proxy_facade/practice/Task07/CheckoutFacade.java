package m08_proxy_facade.practice.task07;

// Facade: один вход checkout, скрывающий каталог + склад + оплату.
// Зависит от интерфейса CatalogService (всё равно, прокси это или реальный) — DIP.
class CheckoutFacade {
    // TODO: поля CatalogService catalog, InventoryService inventory,
    //       PaymentService payment + конструктор, принимающий их

    public String checkout(String sku) {
        // TODO: price = catalog.priceOf(sku);
        //       if (!inventory.reserve(sku)) return "нет на складе";
        //       if (!payment.charge(price)) return "оплата отклонена";
        //       return "оформлено " + sku + " за " + price;
        return "";
    }
}
