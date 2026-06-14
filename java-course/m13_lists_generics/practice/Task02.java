package m13_lists_generics.practice;

/**
 * Задача 02 — Модуль 13: Список чисел (autoboxing)
 *
 * ЗАДАНИЕ:
 *   Создайте List<Integer>, добавьте 5 чисел, посчитайте их сумму
 *   и среднее. Обратите внимание: при add(5) происходит автоупаковка
 *   int → Integer, при суммировании — автораспаковка.
 *
 * ПРИМЕР (для 10, 20, 30, 40, 50):
 *   Сумма: 150
 *   Среднее: 30.0
 *
 * ПОДСКАЗКА:
 *   В цикле for (int x : list) sum += x; распаковка происходит сама.
 */
import java.util.ArrayList;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        // Ваш код здесь
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 150; i++){
            numbers.add(i);
        }
        numbers.add(2000);
        numbers.add(4000);
        numbers.add(8653);

        int sum = 0;

        for (Integer number : numbers) {
            sum = sum + number;
        }

        System.out.println("Summa: :" + sum);
        System.out.println("Srednee: " + ((double) sum / numbers.size()));
    }
}
