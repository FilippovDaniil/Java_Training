package m06_arrays.practice;

import java.sql.SQLOutput;

/**
 * Задача 03 — Модуль 06: Максимум и минимум
 *
 * ЗАДАНИЕ:
 *   Найдите в массиве наибольший и наименьший элементы.
 *
 * ПРИМЕР (для {7, 2, 9, -4, 5}):
 *   Максимум: 9
 *   Минимум: -4
 *
 * ПОДСКАЗКА:
 *   Начните max и min с a[0], затем сравнивайте с остальными.
 */
public class Task03 {
    public static void main(String[] args) {
        int[] a = {7, 2, 9, -4, 5};
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++){
            if (a[i] > max){
                max = a[i];
            }
            if (a[i] < min){
                min = a[i];
            }

        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}
