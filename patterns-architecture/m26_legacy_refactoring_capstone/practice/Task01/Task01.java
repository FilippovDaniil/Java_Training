package m26_legacy_refactoring_capstone.practice.task01;

/**
 * Задача 01 — Тема 26: Strangler Fig (постепенное замещение)
 *
 * ЗАДАНИЕ:
 *   Новую реализацию вводим за фасадом и переключаем трафик по кусочку:
 *     - OrderApi (файл OrderApi.java): String process(String orderId);
 *     - LegacyOrderApi ("legacy: id") и ModernOrderApi ("modern: id");
 *     - StranglerFacade (файл StranglerFacade.java) implements OrderApi: хранит
 *       legacy, modern и Set<String> migrated; process(id) → если id в migrated,
 *       идёт в modern, иначе в legacy.
 *   В main: фасад с migrated = {"A-2"}; обработайте "A-1" (ещё legacy) и "A-2"
 *   (уже modern).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   legacy: A-1
 *   modern: A-2
 *
 * ТРЕБОВАНИЯ:
 *   - клиент видит один OrderApi (фасад), не зная о двух реализациях;
 *   - переключение идёт по кусочку (множество migrated), а не «всё сразу»;
 *   - старое «отмирает» по мере переноса (когда всё в migrated).
 *
 * ПОДСКАЗКА:
 *   return migrated.contains(id) ? modern.process(id) : legacy.process(id);
 *   Так система всё время рабочая, а миграция — постепенная и обратимая.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: StranglerFacade(legacy, modern, Set.of("A-2")); process("A-1") и "A-2"
    }
}
