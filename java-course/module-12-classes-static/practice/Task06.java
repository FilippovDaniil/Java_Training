/**
 * Задача 06 — Модуль 12: Вложенный класс
 *
 * ЗАДАНИЕ:
 *   У класса Computer объявите СТАТИЧЕСКИЙ вложенный класс Processor
 *   с полями model (String) и cores (int) и методом info().
 *   В main создайте процессор через Computer.Processor и выведите
 *   информацию о нём.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Процессор: Intel i7, ядер: 8
 *
 * ПОДСКАЗКА:
 *   Computer.Processor p = new Computer.Processor();
 *   Статический вложенный класс создаётся через имя внешнего класса.
 */
public class Task06 {
    public static void main(String[] args) {
        // Создайте Computer.Processor и выведите его данные
    }
}

class Computer {
    String brand;

    static class Processor {
        String model;
        int cores;

        // TODO: метод info()
    }
}
