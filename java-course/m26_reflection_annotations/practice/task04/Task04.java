package m26_reflection_annotations.practice.task04;

/**
 * Задача 04 — Модуль 26: Динамический вызов метода
 *
 * ЗАДАНИЕ:
 *   Используя рефлексию:
 *     1) создайте объект Greeter через конструктор (newInstance);
 *     2) найдите метод greet(String) и вызовите его через invoke,
 *        передав имя;
 *     3) выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Привет, Мир!
 *
 * ПОДСКАЗКА:
 *   Constructor<?> ctor = Greeter.class.getConstructor();
 *   Object g = ctor.newInstance();
 *   Method m = Greeter.class.getMethod("greet", String.class);
 *   Object res = m.invoke(g, "Мир");
 *   Методы рефлексии бросают проверяемые исключения — обработайте их.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Task04 {
    public static void main(String[] args) throws Exception {
        // Создайте Greeter и вызовите greet через рефлексию
    }
}
