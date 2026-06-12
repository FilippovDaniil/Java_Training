package m14_grasp.practice.task07;

// Бизнес-логика; слабо связана (зависит от интерфейса OrderRepository).
class OrderService {
    // TODO: поле OrderRepository repo + конструктор OrderService(OrderRepository repo)

    public void createOrder(String id) {
        // TODO: создать new Order(id) и сохранить в репозиторий
    }

    public void addItem(String orderId, String sku, int qty, long priceCents) {
        // TODO: найти заказ по id и addLine(...)
        //       (для in-memory изменения видны без повторного save, но можно и сохранить)
    }

    public long total(String orderId) {
        // TODO: найти заказ и вернуть его total()
        return 0;
    }
}
