// Тонкий контроллер: принимает запрос и делегирует сервису, без бизнес-логики.
class OrderController {
    // TODO: поле OrderService service + конструктор OrderController(OrderService service)

    public String placeOrder(String sku, int qty) {
        // TODO: только делегировать service.place(sku, qty)
        return "";
    }
}
