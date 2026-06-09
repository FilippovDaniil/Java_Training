// Тонкий контроллер: координирует, делегирует сервису, без бизнес-логики.
class OrderController {
    // TODO: поле OrderService service + конструктор OrderController(OrderService service)

    public void createOrder(String id) {
        // TODO: делегировать service.createOrder(id)
    }

    public void addItem(String orderId, String sku, int qty, long priceCents) {
        // TODO: делегировать service.addItem(...)
    }

    public long total(String orderId) {
        // TODO: делегировать service.total(orderId)
        return 0;
    }
}
