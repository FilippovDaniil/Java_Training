package m09_strings.practice;

/**
 * Задача 05 — Модуль 09: Форматированный вывод (String.format)
 *
 * ЗАДАНИЕ:
 *   Дан список из трёх товаров (название, цена). Выведите их в виде
 *   аккуратной таблицы с выравниванием, используя String.format
 *   или printf.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерное выравнивание):
 *   Товар          Цена
 *   Хлеб          45.00
 *   Молоко        80.50
 *   Сыр          560.00
 *
 * ПОДСКАЗКА:
 *   "%-12s%8.2f%n" — название влево по ширине 12,
 *   цена с 2 знаками после точки по ширине 8.
 */
public class Task05 {
    public static void main(String[] args) {
        String[] names = {"Hleb", "Moloko", "Sir"};
        double[] prices = {45.0, 80.5, 560.0};
        // Ваш код здесь

        // Заголовок таблицы
        System.out.printf("%-12s%8s%n", "Tovar", "Cena");

        // Вывод строк с товарами
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-12s%8.2f%n", names[i], prices[i]);
        }
    }
}
