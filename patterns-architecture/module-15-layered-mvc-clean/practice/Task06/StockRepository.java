interface StockRepository {
    Stock findById(String id);
    void save(Stock stock);
}
