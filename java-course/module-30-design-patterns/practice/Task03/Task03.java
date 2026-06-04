/**
 * Задача 03 — Модуль 30: Builder
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс Burger с множеством необязательных параметров
 *   (булочка, котлета, сыр, бекон, соус) через паттерн Builder.
 *   1. Поля Burger делаются финальными, конструктор приватный
 *      (принимает Builder).
 *   2. Вложенный static-класс Builder с методами-сеттерами,
 *      возвращающими this (fluent interface), и методом build().
 *   3. В main соберите два разных бургера и выведите их (toString).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Burger{булочка=true, котлета=2, сыр=true, бекон=false, соус='кетчуп'}
 *
 * ПОДСКАЗКА:
 *   new Burger.Builder().cutlets(2).cheese(true).sauce("кетчуп").build();
 *   Каждый сеттер: public Builder cheese(boolean v){ this.cheese=v; return this; }
 */

public class Task03 {
    public static void main(String[] args) {
        // Соберите бургеры через Builder и выведите их
    }
}
