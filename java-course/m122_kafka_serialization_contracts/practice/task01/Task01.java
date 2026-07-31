package m122_kafka_serialization_contracts.practice.task01;

/**
 * Задача 01 — Модуль 122: конверт события (EventEnvelope) вместо «голого» payload
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task01.java (точка входа), EventEnvelope01.java (конверт),
 *               TaskCreatedPayload01.java (полезная нагрузка).
 *
 * ЗАДАНИЕ:
 *   1) Допишите EventEnvelope01: eventId (UUID), type (String), version (int),
 *      occurredAt (Instant), traceId (String), source (String), payload (T).
 *   2) Допишите TaskCreatedPayload01: taskId, title, authorId, status.
 *   3) В runner(...) отправьте в "task.created" два события в конверте:
 *        - eventId = UUID.randomUUID(), type = "task.created", version = 1,
 *          source = "task-tracker", traceId = произвольная строка;
 *        - ключ сообщения = String.valueOf(taskId).
 *   4) Прочитайте топик CLI-консьюмером и убедитесь, что в JSON видны и конверт, и payload.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ (в консьюмере):
 *   {"eventId":"1f0c...","type":"task.created","version":1,"occurredAt":"2026-07-31T10:15:30Z",
 *    "traceId":"trace-1","source":"task-tracker",
 *    "payload":{"taskId":42,"title":"Написать отчёт","authorId":10,"status":"NEW"}}
 *
 * ЦЕЛЬ: научиться отделять техническую метаинформацию события от бизнес-данных — это основа
 *       дедупликации (модуль 123), трассировки и версионирования.
 *
 * ВАЖНО: eventId генерирует ПРОИЗВОДИТЕЛЬ один раз на событие. Если сгенерировать его при
 *   каждой попытке отправки, дедупликация у потребителя перестанет работать — ретрай будет
 *   выглядеть новым событием.
 *
 * ПОДСКАЗКА: occurredAt — момент бизнес-факта (когда задачу создали), а не момент отправки;
 *   различие становится критичным при накоплении событий в outbox (модуль 124).
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, Object> kafka) {
        return args -> {
            // TODO 1: собрать два EventEnvelope01<TaskCreatedPayload01>
            // TODO 2: отправить в "task.created" с ключом = taskId
            // TODO 3: kafka.flush() и печать метаданных отправки
        };
    }
}
