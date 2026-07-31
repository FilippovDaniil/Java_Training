package m120_kafka_producer_spring.practice;

/**
 * Задача 02 — Модуль 120: топики как бины (KafkaAdmin + TopicBuilder)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен запущенный брокер.
 * Запуск в IDE (▶ main).
 *
 * ЗАДАНИЕ:
 *   Объявите топики сквозного проекта бинами, чтобы KafkaAdmin создавал их при старте приложения:
 *     1) NewTopic taskCreated:       имя "task.created",        6 партиций, 1 реплика;
 *     2) NewTopic taskStatusChanged: имя "task.status.changed", 6 партиций, 1 реплика,
 *        c конфигом retention.ms = 604800000 (7 дней);
 *     3) NewTopic taskState:         имя "task.state",          3 партиции, 1 реплика,
 *        cleanup.policy = compact (снимок последнего состояния по ключу).
 *   Запустите приложение и проверьте результат.
 *
 * ПРОВЕРКА:
 *   docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
 *   docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
 *       --describe --topic task.status.changed
 *   (или глазами в kafka-ui на http://localhost:8081)
 *
 * ЦЕЛЬ: научиться декларативно готовить топики для dev/тестов и задавать их конфиги из кода.
 *
 * ВАЖНО: KafkaAdmin только СОЗДАЁТ отсутствующие топики и умеет добавлять партиции;
 *   он не уменьшает партиции и не меняет существующие конфиги. На проде топиками управляет
 *   инфраструктура, а auto.create.topics.enable выключают.
 *
 * ПОДСКАЗКА: TopicBuilder.name(...).partitions(...).replicas(...).config(ключ, значение).build();
 *   ключи конфигов — константы org.apache.kafka.common.config.TopicConfig.
 */

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class Task02 {

    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }

    @Bean
    NewTopic taskCreated() {
        // TODO: task.created — 6 партиций, 1 реплика
        return TopicBuilder.name("task.created").build();
    }

    @Bean
    NewTopic taskStatusChanged() {
        // TODO: task.status.changed — 6 партиций, 1 реплика, retention.ms = 604800000
        return TopicBuilder.name("task.status.changed").build();
    }

    @Bean
    NewTopic taskState() {
        // TODO: task.state — 3 партиции, 1 реплика, cleanup.policy = compact
        return TopicBuilder.name("task.state").build();
    }
}
