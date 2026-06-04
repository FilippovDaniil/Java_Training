/** Альтернативная реализация — «БД». Для задачи просто заглушка. */
class DatabaseProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        // В реальности — запрос к БД
        return java.util.List.of("Монитор", "Наушники");
    }
}
