package m22_oop_encapsulation_polymorphism_interfaces.practice.task06;

/**
 * Задача 06 — Модуль 22: default-метод интерфейса
 *
 * ЗАДАНИЕ:
 *   Объявите интерфейс Device с абстрактным методом name() и
 *   default-методом info(), который выводит "Устройство: <name>".
 *   1. Класс Phone реализует name(), но НЕ переопределяет info()
 *      (использует готовую реализацию).
 *   2. Класс Laptop реализует name() И переопределяет info()
 *      (добавляет своё сообщение).
 *   В main вызовите info() у обоих.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Устройство: Телефон
 *   Ноутбук — мобильный компьютер
 *
 * ПОДСКАЗКА:
 *   interface Device { String name(); default void info() {...} }
 *   default-метод можно использовать как есть или переопределить.
 */

// TODO: классы Phone (без переопределения info) и Laptop (с переопределением)
public class Task06 {
    public static void main(String[] args) {
        // Создайте Phone и Laptop, вызовите info()
    }
}
