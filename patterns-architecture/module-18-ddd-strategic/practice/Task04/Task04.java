/**
 * Задача 04 — Тема 18: Entity vs Value Object на одном примере
 *
 * ЗАДАНИЕ:
 *   Покажите разницу: Person — Entity (равенство по id), Address — Value Object
 *   (равенство по значению).
 *     - Address (файл Address.java): неизменяемые street, city; equals/hashCode
 *       по значению;
 *     - Person (файл Person.java): final id, изменяемые name и Address;
 *       equals/hashCode ТОЛЬКО по id.
 *   В main продемонстрируйте:
 *     1) два Address с одинаковыми street/city равны (equals == true);
 *     2) два Person с РАЗНЫМИ id, но одинаковым адресом — НЕ равны (false);
 *     3) два Person с ОДНИМ id — равны, даже если адреса разные.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Адреса равны по значению: true
 *   Разные id, один адрес — равны: false
 *   Один id, разные адреса — равны: true
 *
 * ТРЕБОВАНИЯ:
 *   - Address — VO (неизменяем, равенство по значению, без id);
 *   - Person — Entity (равенство по id, поля могут меняться);
 *   - выводы 1–3 подтверждают разницу.
 *
 * ПОДСКАЗКА:
 *   Вопрос-тест: «важно, КАКОЙ это объект (id) или ЧТО за значение?». Person —
 *   первое, Address — второе.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: продемонстрируйте 3 сравнения из условия
    }
}
