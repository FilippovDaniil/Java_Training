package m09_strings.practice;

/**
 * Задача 06 — Модуль 09: Разбиение строки (split)
 *
 * ЗАДАНИЕ:
 *   Дана строка с числами через запятую, например "5,12,3,8,1".
 *   Разбейте её, найдите сумму и максимум чисел.
 *
 * ПРИМЕР:
 *   "5,12,3,8,1" → Сумма: 29, Максимум: 12
 *
 * ПОДСКАЗКА:
 *   String[] parts = s.split(",");
 *   Каждую часть преобразуйте в число: Integer.parseInt(parts[i]).
 */
public class Task06 {
    public static void main(String[] args) {
        String data = "5,12,3,8,1";
        String[] a = data.split(",");
        int max = 0;
        int sum = 0;
        for (String stringInA : a) {
            int number = Integer.parseInt(stringInA);
            if (number > max){
                max = number;
            }
            sum = sum + number;
        }

        System.out.println("Max: " + max);
        System.out.println("Sum: " + sum);

        // Ваш код здесь
    }
}
