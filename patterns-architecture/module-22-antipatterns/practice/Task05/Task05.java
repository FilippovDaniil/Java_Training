/**
 * Задача 05 — Тема 22: лечим Long Parameter List
 *
 * ЗАДАНИЕ:
 *   Раньше бронирование принимало кучу аргументов:
 *   book(String guest, String room, int nights, boolean breakfast, String note) —
 *   легко перепутать порядок. Введите параметр-объект:
 *     - BookingRequest (файл BookingRequest.java): поля guest, room, nights,
 *       breakfast (+ можно note); геттеры (или public final поля);
 *     - BookingService (файл BookingService.java): String book(BookingRequest req)
 *       → "Бронь: <guest>, <room>, <nights> ноч., завтрак=<breakfast>".
 *   В main соберите BookingRequest и забронируйте через него.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Бронь: Анна, 101, 3 ноч., завтрак=true
 *
 * ТРЕБОВАНИЯ:
 *   - book принимает ОДИН параметр-объект, а не длинный список;
 *   - поля сгруппированы в осмысленный объект;
 *   - порядок аргументов больше нельзя перепутать.
 *
 * ПОДСКАЗКА:
 *   Параметр-объект (или Builder, тема 05) делает вызов читаемым и устойчивым к
 *   ошибкам порядка. Часто длинный список — это спрятанное понятие.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: соберите BookingRequest и вызовите BookingService.book(req)
    }
}
