package m15_layered_mvc_clean.practice.task06;

// Presentation: делегирует сервису, без бизнес-правил.
class StockController {
    // TODO: поле StockService service + конструктор StockController(StockService service)

    public void addStock(String id, int qty) {
        // TODO: делегировать service.addStock(id, qty)
    }

    public int quantity(String id) {
        // TODO: делегировать service.quantity(id)
        return 0;
    }
}
