package m06_arrays.practice;

/**
 * Задача 02 — Модуль 06: Сумма и среднее
 *
 * ЗАДАНИЕ:
 *   Дан массив чисел. Посчитайте их сумму и среднее арифметическое
 *   (среднее — дробное число).
 *
 * ПРИМЕР (для {4, 8, 15, 16, 23, 42}):
 *   Сумма: 108
 *   Среднее: 18.0
 *
 * ПОДСКАЗКА:
 *   Среднее = (double) сумма / a.length
 */
public class Task02 {
    public static void main(String[] args) {
        int[] a = {4, 8, 15, 16, 23, 42};
        int sum = 0;
        for (int i = 0; i < a.length; i++){
            sum = sum + a[i];
        }
        System.out.println("Sum: " + sum);
        System.out.println("Srednee: " + (double) sum / a.length);
    }
}
