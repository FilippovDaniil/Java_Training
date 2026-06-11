package m103_spring_test_config.practice.task05;

/**
 * Задача 05 — Модуль 103: @TestConfiguration — подмена бина в тесте
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   В приложении есть бин Clock (системные часы). В тесте подмените его ФИКСИРОВАННЫМ,
 *   чтобы результат был детерминированным:
 *     1) Объявите @TestConfiguration TestClockConfig05 с @Bean Clock fixedClock()
 *          = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC).
 *     2) Тест: @SpringBootTest(classes = Task05.class) + @Import(TestClockConfig05.class).
 *     3) @Autowired ReportService05 service;
 *     4) uses_fixed_clock(): assertThat(service.today()).isEqualTo("2026-01-01").
 *
 * ЦЕЛЬ: заменить реальный бин на тестовый дубль через @TestConfiguration + @Import.
 *
 * ВАЖНО: @TestConfiguration НЕ автосканируется — её подключают явно @Import (не «течёт» в др. тесты).
 *
 * ПОДСКАЗКА: ReportService05.today() форматирует LocalDate.now(clock) → "yyyy-MM-dd".
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;

public class Task05 {
    public static void main(String[] args) { SpringApplication.run(Task05.class, args); }

    @Bean
    Clock systemClock() { return Clock.system(ZoneId.of("UTC")); }   // реальные часы (прод)
}
