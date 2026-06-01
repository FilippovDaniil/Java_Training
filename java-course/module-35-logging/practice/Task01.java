/**
 * Задача 01 — Модуль 35: Первый логгер и уровни
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1. Создайте статический логгер для этого класса.
 *   2. В main выведите сообщения ВСЕХ пяти уровней: trace, debug,
 *      info, warn, error.
 *   3. Запустите и понаблюдайте: по умолчанию (уровень INFO) trace и
 *      debug НЕ появятся в выводе.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно, при уровне INFO):
 *   ... INFO  ... - Информационное сообщение
 *   ... WARN  ... - Предупреждение
 *   ... ERROR ... - Ошибка
 *
 * ПОДСКАЗКА:
 *   private static final Logger log = LoggerFactory.getLogger(Task01.class);
 *   log.info("..."); log.debug("...");
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task01 {
    private static final Logger log = LoggerFactory.getLogger(Task01.class);

    public static void main(String[] args) {
        // Выведите сообщения всех пяти уровней
    }
}
