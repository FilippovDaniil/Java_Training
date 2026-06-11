package m35_logging.practice;

/**
 * Задача 04 — Модуль 35: Конфигурация Logback (logback.xml)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ (конфигурация + код):
 *   1. Создайте файл src/main/resources/logback.xml с ДВУМЯ
 *      аппендерами: CONSOLE (в консоль) и FILE (в файл app.log).
 *      Задайте паттерн с временем, уровнем и логгером.
 *      Корневой уровень — INFO.
 *   2. В коде ниже выведите несколько сообщений разного уровня.
 *   3. Запустите и проверьте: сообщения попали И в консоль, И в app.log.
 *
 * ПРИМЕР logback.xml — см. theory.md (раздел «Конфигурация»).
 *
 * ПРОВЕРКА:
 *   После запуска появился файл app.log с теми же записями, что в консоли.
 *
 * ПОДСКАЗКА:
 *   logback.xml должен лежать в classpath (src/main/resources).
 *   FileAppender пишет в файл, ConsoleAppender — в консоль; оба
 *   подключаются в <root>.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task04 {
    private static final Logger log = LoggerFactory.getLogger(Task04.class);

    public static void main(String[] args) {
        // Выведите сообщения; проверьте, что они идут и в консоль, и в файл
    }
}
