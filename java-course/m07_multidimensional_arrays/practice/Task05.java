package m07_multidimensional_arrays.practice;

/**
 * Задача 05 — Модуль 07: Максимум и его координаты
 *
 * ЗАДАНИЕ:
 *   Найдите наибольший элемент матрицы и выведите его значение
 *   вместе с координатами (номер строки и столбца).
 *
 * ПРИМЕР:
 *   матрица { {3, 8, 1}, {9, 2, 7}, {4, 6, 5} }
 *   Вывод: Максимум 9 в позиции [1][0]
 *
 * ПОДСКАЗКА:
 *   Храните не только значение max, но и индексы maxRow, maxCol.
 */
public class Task05 {
    public static void main(String[] args) {
        int[][] m = {
            {3, 8, 1},
            {1, 2, 7},
            {4, 6, 9}
        };
        // Ваш код здесь
        int max = m[0][0];
        int max_row = 0;
        int max_col = 0;

        for (int i = 0; i < m.length; i++) {       // i — номер строки
            for (int j = 0; j < m[i].length; j++) { // j — номер столбца
                if (m[i][j] > max){
                    max = m[i][j];
                    max_row = i;
                    max_col = j;
                }
            }
        }

        System.out.println("Max: " + max + " position: [" + max_row + "] [" + max_col + "]");
    }
}
