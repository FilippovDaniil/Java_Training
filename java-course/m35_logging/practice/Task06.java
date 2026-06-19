package m35_logging.practice;

/**
 * Задача 06 — Модуль 35: MDC (контекст запроса)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ:
 *   Смоделируйте обработку нескольких «запросов», добавляя в MDC
 *   идентификатор requestId. Все логи внутри обработки одного запроса
 *   должны нести его requestId.
 *   1. В цикле для каждого запроса: MDC.put("requestId", ...);
 *   2. залогируйте несколько шагов обработки;
 *   3. в конце MDC.clear() (или remove).
 *   Чтобы requestId попадал в вывод, в logback.xml используйте в
 *   паттерне %X{requestId}.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример с паттерном [%X{requestId}]):
 *   [req-1] INFO - Шаг 1
 *   [req-1] INFO - Шаг 2
 *   [req-2] INFO - Шаг 1
 *
 * ПОДСКАЗКА:
 *   import org.slf4j.MDC;
 *   MDC.put("requestId", "req-1"); ... MDC.clear();
 *   Паттерн logback: %X{requestId} подставит значение из MDC.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Task06 {
    private static final Logger log = LoggerFactory.getLogger(Task06.class);

    public static void main(String[] args) {
        System.out.println("=== Демонстрация MDC (контекст запроса) ===\n");

        // Обработка нескольких запросов
        processRequest("req-001", "Пользователь Иван");
        processRequest("req-002", "Пользователь Мария");
        processRequest("req-003", "Пользователь Петр");

        // Дополнительная демонстрация с вложенными операциями
        System.out.println("\n--- Вложенные операции ---");
        processRequestWithNested("req-004", "Пользователь Анна");

        System.out.println("\n✅ Проверьте логи - каждый запрос имеет свой requestId");
    }

    private static void processRequest(String requestId, String user) {
        // Устанавливаем requestId в MDC
        MDC.put("requestId", requestId);
        MDC.put("user", user);

        log.info("Начало обработки запроса");
        log.info("Пользователь: {}", user);

        // Имитация шагов обработки
        step1();
        step2();
        step3();

        log.info("Запрос успешно обработан");

        // Очищаем MDC после обработки запроса
        MDC.clear();

        // Небольшая пауза для наглядности
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void step1() {
        log.info("Шаг 1: Валидация входных данных");
        // Имитация работы
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void step2() {
        log.info("Шаг 2: Обработка данных");
        // Имитация работы
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void step3() {
        log.info("Шаг 3: Сохранение результата");
        // Имитация работы
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Пример с вложенными операциями
    private static void processRequestWithNested(String requestId, String user) {
        MDC.put("requestId", requestId);
        MDC.put("user", user);

        log.info("Начало обработки запроса с вложенными операциями");

        // Внутренний метод использует тот же MDC
        validateUser(user);
        processData(user);
        sendNotification(user);

        log.info("Запрос с вложенными операциями завершен");

        MDC.clear();
    }

    private static void validateUser(String user) {
        log.info("Валидация пользователя: {}", user);
        // Имитация валидации
        if (user == null || user.trim().isEmpty()) {
            log.warn("Пустое имя пользователя");
        } else {
            log.debug("Пользователь {} прошел валидацию", user);
        }
    }

    private static void processData(String user) {
        log.info("Обработка данных для пользователя: {}", user);
        // Имитация обработки
        log.debug("Данные пользователя {} обработаны", user);
    }

    private static void sendNotification(String user) {
        log.info("Отправка уведомления пользователю: {}", user);
        // Имитация отправки
        log.debug("Уведомление отправлено {}", user);
    }
}
