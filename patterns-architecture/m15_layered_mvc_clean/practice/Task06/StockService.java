package m15_layered_mvc_clean.practice.task06;

// Business: бизнес-правило (qty > 0) живёт здесь.
class StockService {
    // TODO: поле StockRepository repo + конструктор StockService(StockRepository repo)

    public void addStock(String id, int qty) {
        // TODO: если qty <= 0 → throw new IllegalArgumentException("количество должно быть > 0");
        //       иначе найти Stock, addQty(qty), save
    }

    public int quantity(String id) {
        // TODO: вернуть getQty найденного Stock
        return 0;
    }
}
