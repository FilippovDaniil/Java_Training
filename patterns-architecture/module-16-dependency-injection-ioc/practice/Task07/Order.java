// Данные заказа.
class Order {
    private final String id;
    private final long amountCents;

    Order(String id, long amountCents) {
        this.id = id;
        this.amountCents = amountCents;
    }

    String getId() { return id; }
    long getAmountCents() { return amountCents; }
}
