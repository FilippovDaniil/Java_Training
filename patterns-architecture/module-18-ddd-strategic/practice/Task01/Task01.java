/**
 * Задача 01 — Тема 18: Value Object (Money)
 *
 * ЗАДАНИЕ:
 *   Реализуйте Money (файл Money.java) как неизменяемый объект-значение:
 *     - приватные final поля amountCents (long), currency (String);
 *     - конструктор с проверкой (amountCents >= 0, currency не пустой);
 *     - метод plus(Money other) — возвращает НОВЫЙ Money (валюты должны совпадать);
 *     - equals()/hashCode() по ЗНАЧЕНИЮ (amountCents + currency);
 *     - toString() вида "100 RUB".
 *   В main: создайте два одинаковых по значению Money, сравните через equals
 *   (true), сложите два Money, выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   100 RUB равно 100 RUB: true
 *   100 RUB + 50 RUB = 150 RUB
 *
 * ТРЕБОВАНИЯ:
 *   - Money неизменяем (поля final, операции возвращают новый объект);
 *   - равенство по значению (два разных объекта с одинаковыми полями equals==true);
 *   - plus при разных валютах бросает исключение.
 *
 * ПОДСКАЗКА:
 *   Value Object: «важно ЧТО, а не КТО». Для equals/hashCode используйте оба поля;
 *   plus: new Money(this.amountCents + other.amountCents, currency).
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте два равных по значению Money, сравните equals; сложите два Money
    }
}
