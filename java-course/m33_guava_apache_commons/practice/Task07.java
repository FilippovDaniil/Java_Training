package m33_guava_apache_commons.practice;

/**
 * Задача 07 — Модуль 33 (МИНИ-ПРОЕКТ): Анализатор логов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.google.guava:guava:33.2.1-jre
 *   org.apache.commons:commons-lang3:3.14.0
 *
 * ЗАДАНИЕ:
 *   Дан массив строк лога формата "LEVEL;module;message", например:
 *     "INFO;auth;Вход выполнен"
 *     "ERROR;db;Нет соединения"
 *     "INFO;auth;Выход"
 *     "WARN;db;Медленный запрос"
 *     "ERROR;auth;Неверный пароль"
 *   Используя Guava и Commons, постройте отчёт:
 *     1) Splitter — распарсите каждую строку на части;
 *     2) Multiset — посчитайте количество записей каждого уровня
 *        (INFO/WARN/ERROR);
 *     3) Multimap — сгруппируйте сообщения по модулю (module -> messages);
 *     4) StringUtils — пропускайте пустые/некорректные строки
 *        (isBlank), при необходимости приводите уровень к верхнему
 *        регистру (upperCase);
 *     5) Joiner — выведите для каждого модуля его сообщения через "; ".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   === Уровни ===
 *   INFO: 2
 *   WARN: 1
 *   ERROR: 2
 *   === По модулям ===
 *   auth: Вход выполнен; Выход; Неверный пароль
 *   db: Нет соединения; Медленный запрос
 *
 * ПОДСКАЗКА:
 *   Объедините инструменты модуля: Splitter для парсинга, Multiset для
 *   подсчёта уровней, Multimap для группировки, StringUtils для
 *   защиты от «грязных» данных, Joiner для вывода.
 */
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import org.apache.commons.lang3.StringUtils;

public class Task07 {
    public static void main(String[] args) {
        String[] logs = {
                "INFO;auth;Вход выполнен",
                "ERROR;db;Нет соединения",
                "INFO;auth;Выход",
                "WARN;db;Медленный запрос",
                "ERROR;auth;Неверный пароль",
                "",  // Пустая строка
                "INFO;;Сообщение без модуля",
                "ERROR;db;", // Сообщение пустое
                "  DEBUG;cache;Тестовое сообщение  ",
                null, // null строка
                "WARN;db;Долгий запрос",
                "INFO;auth;Повторный вход"
        };

        System.out.println("=== АНАЛИЗАТОР ЛОГОВ ===\n");

        // Создаем анализатор и обрабатываем логи
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeLogs(logs);

        // Выводим отчет
        analyzer.printReport();

        // Дополнительная статистика
        System.out.println("\n" + "=" .repeat(60));
        System.out.println("\nДополнительная статистика:");
        analyzer.printAdditionalStats();
    }
}


