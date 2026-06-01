/**
 * Задача 07 — Модуль 20 (МИНИ-ПРОЕКТ): Калькулятор возраста и событий
 *
 * ЗАДАНИЕ:
 *   Считайте дату рождения в формате "dd.MM.yyyy" и вычислите:
 *     1) точный возраст в формате "X лет, Y месяцев, Z дней" (Period);
 *     2) сколько полных дней прожито (ChronoUnit.DAYS);
 *     3) день недели, в который человек родился;
 *     4) сколько дней осталось до следующего дня рождения;
 *     5) дату следующего дня рождения и день недели на неё.
 *
 * ПРИМЕР:
 *   Ввод: 15.05.2000
 *   Возраст: 26 лет, 0 месяцев, 17 дней
 *   Прожито дней: 9514
 *   Родился в: MONDAY
 *   До следующего дня рождения: 348 дней (15.05.2027, SATURDAY)
 *
 * ПОДСКАЗКИ:
 *   - разбор даты: LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
 *   - следующий ДР: возьмите день/месяц рождения с текущим годом
 *     (birth.withYear(today.getYear())); если он уже прошёл —
 *     прибавьте 1 год (plusYears(1));
 *   - обработайте неверный формат ввода через try-catch.
 */
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        // Ваш код здесь

        scanner.close();
    }
}
