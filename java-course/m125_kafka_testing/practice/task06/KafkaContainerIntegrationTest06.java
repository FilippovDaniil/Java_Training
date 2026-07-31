package m125_kafka_testing.practice.task06;

/**
 * Задача 06 — Модуль 125: Testcontainers — интеграция с настоящим брокером
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-boot-starter-test, org.testcontainers:kafka,
 *   org.testcontainers:junit-jupiter, awaitility. НУЖЕН запущенный Docker (Rancher/Docker Desktop).
 * Тест-класс без main.
 *
 * ФАЙЛЫ ЗАДАЧИ: KafkaContainerIntegrationTest06.java (тест), Task06.java (тестовое приложение),
 *               OrderListener06.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   1) Поднимите брокер контейнером:
 *        @Testcontainers
 *        @Container static final KafkaContainer kafka =
 *            new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.0"));
 *        @DynamicPropertySource static void props(DynamicPropertyRegistry r) {
 *            r.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
 *            r.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
 *        }
 *   2) end_to_end_publish_consume(): опубликовать событие и дождаться эффекта в слушателе.
 *   3) order_is_preserved_within_key(): отправить 20 событий с одним ключом и проверить, что
 *      слушатель получил их в том же порядке (сравнение списков).
 *   4) survives_broker_restart(): остановить и снова запустить контейнер брокера
 *      (kafka.stop() / kafka.start() или через Testcontainers API), убедиться, что после
 *      восстановления слушатель продолжает работу без потери накопленных событий.
 *      Если этот пункт нестабилен на вашей машине — зафиксируйте наблюдение в комментарии.
 *   5) Сравните время выполнения этого класса с задачей 03 (@EmbeddedKafka) и запишите вывод:
 *      какие проверки стоят Docker'а, а какие нет.
 *
 * ЦЕЛЬ: получить «последнюю милю» доверия — тест на том же брокере, что в проде, включая
 *       поведение при рестарте и настоящие конфиги топиков.
 *
 * ВАЖНО: контейнер объявляется static и переиспользуется всем классом; @Container на нестатическом
 *   поле поднимает НОВЫЙ брокер на каждый тест — набор тестов станет неприлично долгим.
 *
 * ПОДСКАЗКА: такие тесты выносят в отдельный набор (integrationTest / тег @Tag("integration")),
 *   чтобы обычная сборка не требовала Docker. В CI — отдельный шаг pipeline.
 */

import org.junit.jupiter.api.Test;

class KafkaContainerIntegrationTest06 {

    @Test
    void end_to_end_publish_consume() {
        // TODO 2
    }

    @Test
    void order_is_preserved_within_key() {
        // TODO 3
    }

    @Test
    void survives_broker_restart() {
        // TODO 4
    }
}
