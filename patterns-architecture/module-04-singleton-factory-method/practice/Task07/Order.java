/** Данные заказа (готово, менять не нужно). */
class Order {
    private final String number;
    private final String sku;
    private final long priceCents;

    Order(String number, String sku, long priceCents) {
        this.number = number;
        this.sku = sku;
        this.priceCents = priceCents;
    }

    String getNumber() { return number; }
    String getSku() { return sku; }
    long getPriceCents() { return priceCents; }
}
