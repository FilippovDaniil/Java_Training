package m14_grasp.practice.task03;

/**
 * Задача 03 — Тема 14: Low Coupling (слабая связанность)
 *
 * ЗАДАНИЕ:
 *   Снизьте связанность сервиса с хранилищем: пусть он зависит от ИНТЕРФЕЙСА,
 *   а конкретику получает снаружи:
 *     - интерфейс OrderRepository (файл OrderRepository.java): List<Long>
 *       allTotals();
 *     - InMemoryOrderRepository (файл InMemoryOrderRepository.java) — реализация
 *       на списке;
 *     - RevenueService (файл RevenueService.java): получает OrderRepository в
 *       конструкторе, метод totalRevenue() суммирует.
 *   В main создайте репозиторий, внедрите в сервис, выведите выручку.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Выручка: 60000
 *
 * ТРЕБОВАНИЯ:
 *   - RevenueService НЕ создаёт реализацию репозитория внутри (нет new ...Repository());
 *   - сервис знает только интерфейс — связанность с конкретикой нулевая;
 *   - заменить хранилище можно без правок сервиса.
 *
 * ПОДСКАЗКА:
 *   Low Coupling на практике = зависимость на абстракцию + внедрение через
 *   конструктор (это же DIP, тема 03; механику DI разберём в теме 16).
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: создайте InMemoryOrderRepository, внедрите в RevenueService, выведите totalRevenue()
    }
}
