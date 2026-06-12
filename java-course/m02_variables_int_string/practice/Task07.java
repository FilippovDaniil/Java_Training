package m02_variables_int_string.practice;

/**
 * Задача 07 — Модуль 02 (МИНИ-ПРОЕКТ): Чек магазина
 *
 * ЗАДАНИЕ:
 *   Смоделируйте простой кассовый чек. Заданы товары и их данные:
 *     - "Хлеб",  цена 45,  количество 2
 *     - "Молоко", цена 80, количество 3
 *   Посчитайте стоимость каждой позиции (цена * количество)
 *   и общую сумму, затем выведите чек.
 *
 * ПРИМЕР ВЫВОДА:
 *   === ЧЕК ===
 *   Хлеб x2 = 90
 *   Молоко x3 = 240
 *   -----------
 *   ИТОГО: 330
 *
 * ТРЕБОВАНИЯ:
 *   - используйте переменные типа int для цен и количества
 *   - используйте переменные типа String для названий
 *   - итог посчитайте арифметически, не «зашивайте» 330 вручную
 *
 * ПОДСКАЗКА:
 *   Промежуточные суммы храните в отдельных int-переменных,
 *   затем сложите их для итога.
 */
public class Task07 {
    public static void main(String[] args) {
        String name_of_one_good = "Hleb";
        int price_of_one = 45;
        int count_of_one = 2;
        String name_of_two_good = "Moloko";
        int price_of_two = 80;
        int count_of_two = 3;
        int sum_of_one = price_of_one * count_of_one;
        int sum_of_two = price_of_two * count_of_two;
        int sum = sum_of_one + sum_of_two;
        System.out.println("===== Check =====");
        System.out.println(name_of_one_good + " x" + count_of_one + " = " + sum_of_one);
        System.out.println(name_of_two_good + " x" + count_of_two + " = " + sum_of_two);
        System.out.println("=================");
        System.out.println("Itogo: " + sum);
    }
}
