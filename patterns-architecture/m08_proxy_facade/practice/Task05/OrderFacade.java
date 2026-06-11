package m08_proxy_facade.practice.task05;

// Facade: единая операция placeOrder, координирующая три подсистемы.
class OrderFacade {
    private final Inventory inventory = new Inventory();
    private final Payment payment = new Payment();
    private final Shipping shipping = new Shipping();

    public String placeOrder(String sku, long amountCents) {
        // TODO: 1) inventory.reserve(sku) — нет → "нет на складе";
        //       2) payment.charge(amountCents) — нет → "оплата отклонена";
        //       3) id = shipping.schedule(sku) → "заказ оформлен, отправление " + id
        return "";
    }
}
