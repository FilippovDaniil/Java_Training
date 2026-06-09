// Единый источник сквозной нумерации заказов (Singleton).
class OrderNumberGenerator {
    // TODO: реализуйте Singleton (eager/holder/enum-стиль на ваш выбор)
    // TODO: приватное поле счётчика

    // TODO: public static OrderNumberGenerator getInstance()

    public synchronized String next() {
        // TODO: вернуть "OPS-" + (++counter)
        return "";
    }
}
