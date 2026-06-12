package m03_solid_lsp_isp_dip.practice.task05;

/**
 * Задача 05 — Тема 03: DIP — зависеть от абстракции
 *
 * ЗАДАНИЕ:
 *   Сервис отчёта о выручке не должен «прибиваться» к конкретному хранилищу.
 *     - интерфейс OrderRepository (файл OrderRepository.java):
 *         List<Long> findAllAmountsCents();   // суммы заказов
 *     - InMemoryOrderRepository — реализация на List (деталь);
 *     - RevenueReportService (файл RevenueReportService.java): получает
 *       OrderRepository В КОНСТРУКТОРЕ и метод long totalRevenue()
 *       суммирует все заказы. Сервис НЕ создаёт хранилище сам.
 *   В main создайте репозиторий, передайте в сервис, выведите выручку.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Выручка: 45000
 *
 * ТРЕБОВАНИЯ:
 *   - в RevenueReportService нет 'new InMemoryOrderRepository()' —
 *     зависимость приходит снаружи (внедрение через конструктор);
 *   - сервис знает только интерфейс OrderRepository, не его реализацию;
 *   - заменить хранилище можно без единой правки в сервисе.
 *
 * ПОДСКАЗКА:
 *   Абстракцию OrderRepository «заказывает» бизнес-слой (сервис), а конкретная
 *   реализация (InMemory, в будущем — БД) под неё подстраивается. Это и есть
 *   инверсия зависимости — фундамент DI (тема 16) и гексагона (тема 17).
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: создайте InMemoryOrderRepository с суммами, внедрите в
        //       RevenueReportService, выведите totalRevenue()
    }
}
