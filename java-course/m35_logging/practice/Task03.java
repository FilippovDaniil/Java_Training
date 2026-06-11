package m35_logging.practice;

/**
 * Задача 03 — Модуль 35: Логирование исключений
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ:
 *   В блоке try-catch спровоцируйте исключение (например, деление на
 *   ноль или Integer.parseInt("abc")). В catch залогируйте его через
 *   log.error, передав исключение ПОСЛЕДНИМ аргументом — чтобы в логе
 *   был полный стектрейс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   ERROR - Не удалось разобрать число: abc
 *   java.lang.NumberFormatException: For input string: "abc"
 *       at ...
 *
 * ПОДСКАЗКА:
 *   log.error("Не удалось разобрать число: {}", input, e);
 *   Исключение передаётся БЕЗ {} — отдельным последним аргументом.
 *   Сравните с e.printStackTrace() — почему логирование лучше?
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task03 {
    private static final Logger log = LoggerFactory.getLogger(Task03.class);

    public static void main(String[] args) {
        String input = "abc";
        // Спровоцируйте и залогируйте исключение через log.error(..., e)
    }
}
