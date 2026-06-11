package m18_ddd_strategic.practice.task02;

/**
 * Задача 02 — Тема 18: Value Object с валидацией и нормализацией (Email)
 *
 * ЗАДАНИЕ:
 *   Реализуйте Email (файл Email.java) как самопроверяющийся Value Object:
 *     - приватное final поле value (String);
 *     - конструктор: проверяет, что строка непуста и содержит '@' и '.',
 *       иначе IllegalArgumentException; НОРМАЛИЗУЕТ к нижнему регистру;
 *     - equals()/hashCode() по значению; toString() возвращает value.
 *   В main: создайте Email из "User@Mail.COM" (хранится как "user@mail.com"),
 *   сравните с Email("user@mail.com") через equals (true), попробуйте создать
 *   некорректный (поймайте исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Email: user@mail.com
 *   Равны (регистр не важен): true
 *   Ошибка: неверный e-mail
 *
 * ТРЕБОВАНИЯ:
 *   - валидация и нормализация — в конструкторе (объект всегда валиден);
 *   - "User@Mail.COM" и "user@mail.com" равны (нормализация к нижнему регистру);
 *   - VO неизменяем, равенство по значению.
 *
 * ПОДСКАЗКА:
 *   Самопроверяющийся VO нельзя создать в некорректном состоянии. Нормализация
 *   (toLowerCase) делает равенство устойчивым к регистру.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: Email из "User@Mail.COM"; сравните с Email("user@mail.com");
        //       попробуйте некорректный e-mail (поймайте исключение)
    }
}
