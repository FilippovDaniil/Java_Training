package m15_layered_mvc_clean.practice.task06;

interface StockRepository {
    Stock findById(String id);
    void save(Stock stock);
}
