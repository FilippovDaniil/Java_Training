package m06_arrays.practice;

/**
 * Задача 05 — Модуль 06: Подсчёт по условию
 *
 * ЗАДАНИЕ:
 *   Посчитайте, сколько в массиве чётных и сколько нечётных чисел.
 *
 * ПРИМЕР (для {1, 2, 3, 4, 5, 6}):
 *   Чётных: 3
 *   Нечётных: 3
 *
 * ПОДСКАЗКА:
 *   Заведите два счётчика, в цикле проверяйте x % 2 == 0.
 *   Удобно использовать for-each.
 */
public class Task05 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int count_chet = 0;
        int count_nechet = 0;
        for (int i : a) {
            if (i % 2 == 0){
                count_chet++;
            }else{
                count_nechet++;
            }
        }
        System.out.println("Chet: " + count_chet);
        System.out.println("Nechet: " + count_nechet);
    }
}
