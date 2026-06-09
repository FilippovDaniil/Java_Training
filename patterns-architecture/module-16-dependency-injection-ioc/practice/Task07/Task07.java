/**
 * Задача 07 — Тема 16 (МИНИ-ПРОЕКТ OPS): сборка приложения контейнером
 *
 * Развитие OPS. Слои из темы 15 (Controller→Service→Repository) теперь
 * связываются НЕ вручную, а через IoC-контейнер (DI).
 *
 * ЗАДАНИЕ:
 *   1. Order (файл Order.java): данные id, amountCents.
 *   2. OrderRepository (файл ...) interface save/findById; InMemoryOrderRepository.
 *   3. OrderService (файл OrderService.java): зависит от OrderRepository
 *      (конструктор); place(id, amount) сохраняет заказ; amount(id) возвращает сумму.
 *   4. OrderController (файл OrderController.java): зависит от OrderService; делегирует.
 *   5. Container (файл Container.java): register(Class, Supplier) / resolve(Class).
 *   В main: зарегистрируйте в контейнере OrderRepository, OrderService (его рецепт
 *   берёт репозиторий через resolve), OrderController (берёт сервис через resolve);
 *   затем resolve(OrderController.class), оформите заказ и выведите его сумму.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Сумма заказа A-1: 50000
 *
 * ТРЕБОВАНИЯ:
 *   - граф Controller→Service→Repository собирает контейнер (в main нет ручной
 *     цепочки new OrderController(new OrderService(new InMemoryOrderRepository())));
 *   - каждый слой получает зависимость через конструктор (DI);
 *   - resolve(OrderController.class) возвращает полностью связанный контроллер.
 *
 * ПОДСКАЗКА:
 *   Это ровно то, что делает Spring при старте: читает конфигурацию бинов и
 *   собирает граф зависимостей. Здесь конфигурация — это вызовы register(...).
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите граф через Container (register Repo→Service→Controller),
        //       resolve(OrderController.class), place("A-1", 50000), вывести amount("A-1")
    }
}
