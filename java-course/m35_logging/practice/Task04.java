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
        System.out.println("=== Демонстрация логирования Logback ===\n");

        // Вывод сообщений разных уровней
        log.trace("TRACE сообщение - должно быть пропущено (уровень ниже INFO)");
        log.debug("DEBUG сообщение - должно быть пропущено (уровень ниже INFO)");
        log.info("INFO сообщение - должно появиться в консоли и файле");
        log.warn("WARN сообщение - должно появиться в консоли и файле");
        log.error("ERROR сообщение - должно появиться в консоли и файле");

        // Пример с параметрами
        String user = "Иван";
        int age = 30;
        log.info("Пользователь {} зарегистрирован, возраст: {} лет", user, age);

        // Пример с исключением
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            log.error("Ошибка деления на ноль", e);
        }

        // Дополнительные сообщения для проверки
        log.info("Приложение успешно запущено");
        log.warn("Предупреждение: память заполнена на 80%");
        log.error("Критическая ошибка: соединение с базой данных потеряно");

        System.out.println("\n✅ Проверьте файл app.log - все сообщения уровня INFO и выше должны быть там");
        System.out.println("📁 Файл app.log находится в корне проекта");
    }
}
