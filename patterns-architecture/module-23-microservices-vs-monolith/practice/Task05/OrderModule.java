// Модуль «Заказы» владеет СВОИМИ данными; на товар ссылается по sku через API каталога.
class OrderModule {
    // TODO: поле final CatalogModule catalog + конструктор OrderModule(CatalogModule catalog)
    // TODO: приватный счётчик заказов ordersCount

    public String place(String sku) {
        // TODO: увеличить счётчик; вернуть "заказан: " + catalog.titleOf(sku)
        return "";
    }

    public int ordersCount() {
        // TODO: вернуть число заказов
        return 0;
    }
}
