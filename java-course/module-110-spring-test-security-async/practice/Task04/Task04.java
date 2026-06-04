/**
 * Задача 04 — Модуль 110: тестирование @Async (CompletableFuture)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сервис ReportService04 (готов, @Async, приложение @EnableAsync) возвращает CompletableFuture.
 *   Дождитесь асинхронного результата в тесте:
 *     1) @SpringBootTest(classes = Task04.class); @Autowired ReportService04 service.
 *     2) async_result():
 *          CompletableFuture<String> future = service.generateReport();
 *          String result = future.get(2, TimeUnit.SECONDS);   // ждём с таймаутом
 *          assertThat(result).isEqualTo("READY");
 *
 * ЦЕЛЬ: научиться тестировать асинхронный код — дождаться результата CompletableFuture.
 *
 * ВАЖНО: async выполняется в ДРУГОМ потоке; нельзя проверять результат сразу — нужен future.get(timeout).
 *
 * ПОДСКАЗКА: таймаут защищает тест от зависания, если задача не завершится.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class Task04 {
    public static void main(String[] args) { SpringApplication.run(Task04.class, args); }
}
