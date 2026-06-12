package m24_messaging_event_driven_kafka.practice.task03;

/**
 * Задача 03 — Тема 24: событийная маршрутизация (Event-Driven)
 *
 * ЗАДАНИЕ:
 *   Система реагирует на события по их ТИПУ:
 *     - события OrderCreated(orderId), OrderPaid(orderId) — records (готовы);
 *     - EventRouter (файл EventRouter.java): <T> void on(Class<T> type, Consumer<T> handler);
 *       void dispatch(Object event) — вызывает обработчики, зарегистрированные на
 *       класс события.
 *   В main зарегистрируйте обработчики на OrderCreated ("создан: id") и OrderPaid
 *   ("оплачен: id"), затем отправьте по событию каждого типа.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   создан: A-1
 *   оплачен: A-1
 *
 * ТРЕБОВАНИЯ:
 *   - обработчик вызывается по ТИПУ события (маршрутизация по классу);
 *   - издатель события не знает обработчиков (слабая связанность);
 *   - новый тип события + обработчик добавляются без правок существующих.
 *
 * ПОДСКАЗКА:
 *   Внутри — Map<Class<?>, List<Consumer<Object>>>; dispatch берёт список по
 *   event.getClass(). Это событийная модель: реагируем, а не вызываем напрямую.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: EventRouter; on(OrderCreated.class, ...); on(OrderPaid.class, ...);
        //       dispatch(new OrderCreated("A-1")); dispatch(new OrderPaid("A-1"))
    }
}
