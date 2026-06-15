package m20_date_time.practice;

/**
 * Задача 05 — Модуль 20: DateTimeFormatter
 *
 * ЗАДАНИЕ:
 *   1. Отформатируйте текущую дату-время в строку формата
 *      "dd.MM.yyyy HH:mm".
 *   2. Разберите (parse) строку "25.12.2026" в LocalDate по формату
 *      "dd.MM.yyyy" и выведите день недели этой даты.
 *
 * ПРИМЕР:
 *   Сейчас: 01.06.2026 14:30
 *   25.12.2026 — это FRIDAY
 *
 * ПОДСКАЗКА:
 *   DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
 *   dateTime.format(f);  LocalDate.parse("25.12.2026", dateFormatter).
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task05 {
    public static void main(String[] args) {
        // 1. Форматирование текущей даты-времени
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedNow = now.format(formatter);
        System.out.println("Сейчас: " + formattedNow);

        // 2. Парсинг строки в LocalDate и вывод дня недели
        String dateString = "25.12.2026";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parsedDate = LocalDate.parse(dateString, dateFormatter);
        System.out.println(dateString + " — это " + parsedDate.getDayOfWeek());
    }
}
