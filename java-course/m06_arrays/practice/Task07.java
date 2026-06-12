package m06_arrays.practice;

/**
 * Задача 07 — Модуль 06 (МИНИ-ПРОЕКТ): Погода за неделю
 *
 * ЗАДАНИЕ:
 *   Дан массив температур за 7 дней недели. Выведите аналитику:
 *     - среднюю температуру за неделю (дробное число);
 *     - самый тёплый день (название дня + температуру);
 *     - самый холодный день (название дня + температуру);
 *     - количество дней теплее среднего.
 *
 * ДАНО:
 *   days  = {"Пн","Вт","Ср","Чт","Пт","Сб","Вс"}
 *   temps = {12, 15, 9, 18, 21, 17, 14}
 *
 * ПРИМЕР ВЫВОДА:
 *   Средняя температура: 15.142857142857142
 *   Самый тёплый день: Пт (21)
 *   Самый холодный день: Ср (9)
 *   Дней теплее среднего: 3
 *
 * ПОДСКАЗКА:
 *   Запоминайте ИНДЕКС тёплого/холодного дня, чтобы взять название
 *   из массива days. Среднее посчитайте до подсчёта «теплее среднего».
 */
public class Task07 {
    public static void main(String[] args) {
        String[] days = {"Pn", "Vt", "Sr", "Cht", "Pt", "Sb", "Vs"};
        int[] temps = {12, 15, 9, 18, 21, 17, 14};
        int count_of_warmer_than = 0;
        int index_of_hot = 0;
        int index_of_cold = 0;
        double avg_temp = 0;
        int sum = 0;
        int min = temps[0];
        int max = temps[0];
        for (int i = 0; i < temps.length; i++){
            if (temps[i] > max){
                max = temps[i];
            }
            if (temps[i] < min){
                min = temps[i];
            }
            sum = sum + temps[i];
        }
        avg_temp = sum / 7.0;

        for (int temp : temps) {
            if (temp > avg_temp){
                count_of_warmer_than = count_of_warmer_than + 1;
            }
        }
        index_of_hot = indexOf(temps,max);
        index_of_cold = indexOf(temps,min);
        System.out.println("Srednee: " + avg_temp);
        System.out.println("Most hot day: " + days[index_of_hot] + " (" + max + ")");
        System.out.println("Most cold day: " + days[index_of_cold] + " (" + min + ")");
        System.out.println("Warm then srednee: " + count_of_warmer_than);

    }

    public static int indexOf(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }
}
