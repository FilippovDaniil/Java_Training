package m120_kafka_producer_spring.practice.task03;

/**
 * Задача 03 — Модуль 120: событие-объект и JSON-сериализация (JsonSerializer)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task03.java (точка входа), TaskCreated03.java (событие).
 *
 * ЗАДАНИЕ:
 *   1) Опишите событие TaskCreated03 (см. TODO в его файле): record с полями
 *      id (long), title (String), authorId (long), occurredAt (Instant).
 *   2) Настройте продюсера на JSON:
 *        spring.kafka.producer.value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
 *        spring.kafka.producer.properties.spring.json.add.type.headers: false
 *   3) В runner(...) отправьте два события в "task.created", ключ = String.valueOf(id).
 *   4) Напечатайте партицию и офсет каждой отправки.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ (в CLI-консьюмере): JSON вида
 *   {"id":1,"title":"Написать отчёт","authorId":10,"occurredAt":"2026-07-31T10:15:30Z"}
 *
 * ЦЕЛЬ: научиться публиковать типизированное событие, а не строку, и понимать, что уходит в лог.
 *
 * ВАЖНО: событие — отдельный DTO (record), НЕ JPA-сущность: у сущности ленивые связи и своя
 *   схема, любое её изменение сломает контракт подписчиков.
 *
 * ПОДСКАЗКА: add.type.headers=false убирает заголовок __TypeId__ с именем Java-класса —
 *   иначе потребитель на другом стеке (или в другом пакете) не разберёт сообщение.
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Task03 {

    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, Object> kafka) {
        return args -> {
            // TODO 1: создать два события TaskCreated03 (id 1 и 2, Instant.now())
            // TODO 2: отправить их в "task.created" с ключом String.valueOf(id)
            // TODO 3: напечатать partition/offset из RecordMetadata, затем kafka.flush()
        };
    }
}
