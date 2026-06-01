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
        // Обработайте несколько запросов, помечая логи requestId через MDC
    }
}
