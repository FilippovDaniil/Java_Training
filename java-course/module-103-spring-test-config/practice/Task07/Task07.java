/**
 * Задача 07 — Модуль 103: МИНИ-ПРОЕКТ «Слоистая тестовая конфигурация»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: собрать механизмы модуля воедино — настроить окружение теста из трёх слоёв:
 *       профиль + переопределённое свойство + подменённый бин.
 *
 * КОМПОНЕНТЫ (готовы ниже):
 *   QuotaService07 — читает app.max-tasks (@Value), проверяет canAdd(currentCount);
 *   NotificationGateway07 — интерфейс уведомлений (реальная реализация шлёт во «внешний мир»).
 *
 * ЗАДАНИЕ:
 *   1) Создайте src/test/resources/application-test.properties:
 *          app.environment=test
 *   2) @TestConfiguration TestBeans07 с @Bean NotificationGateway07 — заглушка,
 *      пишущая в список sent (фейк вместо реального шлюза).
 *   3) Тест-класс:
 *        - @SpringBootTest(classes = Task07.class);
 *        - @ActiveProfiles("test");                         // слой 1: профиль + файл
 *        - @TestPropertySource(properties = "app.max-tasks=2");  // слой 2: переопределение свойства
 *        - @Import(TestBeans07.class);                      // слой 3: подмена бина
 *        - @Autowired QuotaService07 quota; @Autowired NotificationGateway07 gateway; @Value("${app.environment}") String env;
 *     4) Тесты:
 *        - env_is_test(): assertThat(env).isEqualTo("test");
 *        - quota_respects_limit(): assertThat(quota.canAdd(1)).isTrue();  assertThat(quota.canAdd(2)).isFalse();
 *        - fake_gateway_used(): gateway.notify("x"); assertThat(((FakeGateway07) gateway).sent()).contains("x").
 *
 * ОЖИДАЕМЫЙ ИТОГ: окружение теста полностью управляемо — профиль, свойства и бины заданы тестом.
 *
 * ПОДСКАЗКА: соедините задачи 01 (профиль), 02 (TestPropertySource), 05 (TestConfiguration).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }
}
