/**
 * Задача 07 — Модуль 16 (МИНИ-ПРОЕКТ): Жизненный цикл заказа
 *
 * ЗАДАНИЕ:
 *   Смоделируйте статусы заказа в интернет-магазине через enum
 *   OrderStatus: NEW, PAID, SHIPPED, DELIVERED, CANCELLED.
 *   Реализуйте «машину состояний»:
 *     - метод next(OrderStatus current) возвращает следующий статус
 *       по цепочке NEW → PAID → SHIPPED → DELIVERED;
 *       DELIVERED и CANCELLED — конечные (next вернёт тот же статус);
 *     - метод canCancel(OrderStatus current): отменить можно только
 *       NEW и PAID (не отправленный заказ);
 *   В main продемонстрируйте: проведите заказ по всем стадиям,
 *   попробуйте отменить на разных этапах.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   NEW -> PAID
 *   PAID -> SHIPPED
 *   SHIPPED -> DELIVERED
 *   DELIVERED -> DELIVERED (конечный)
 *   Можно отменить NEW: true
 *   Можно отменить SHIPPED: false
 *
 * ПОДСКАЗКА:
 *   Используйте switch по статусу в методе next().
 *   Для canCancel можно использовать ||: current == NEW || current == PAID.
 */

public class Task07 {
    public static void main(String[] args) {
        // Проведите заказ по стадиям и проверьте возможность отмены
    }

    // TODO: методы next(OrderStatus) и canCancel(OrderStatus)
}
