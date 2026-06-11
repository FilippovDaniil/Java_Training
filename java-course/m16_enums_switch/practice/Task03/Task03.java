package m16_enums_switch.practice.task03;

/**
 * Задача 03 — Модуль 16: enum с полем и конструктором
 *
 * ЗАДАНИЕ:
 *   Дан enum Coin с номиналами монет. Добавьте каждому значению поле
 *   value (номинал в копейках) через конструктор и геттер getValue().
 *   В main посчитайте суммарную стоимость одной монеты каждого вида.
 *
 * НОМИНАЛЫ:
 *   PENNY(1), NICKEL(5), DIME(10), QUARTER(25)
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   PENNY = 1 коп.
 *   NICKEL = 5 коп.
 *   DIME = 10 коп.
 *   QUARTER = 25 коп.
 *   Всего: 41 коп.
 *
 * ПОДСКАЗКА:
 *   enum Coin { PENNY(1), ...; private final int value;
 *               Coin(int value){this.value=value;} int getValue(){...} }
 */

public class Task03 {
    public static void main(String[] args) {
        // Переберите Coin.values(), выведите номиналы и сумму
    }
}
