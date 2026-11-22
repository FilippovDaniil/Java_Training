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
import org.springframework.kafka.support.SendResult;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, Object> kafka) {
        return args -> {
            TaskCreatedPayload01 first = new TaskCreatedPayload01(
                    42L, "Написать отчёт", 10L, "NEW");
            TaskCreatedPayload01 second = new TaskCreatedPayload01(
                    43L, "Проверить почту", 11L, "NEW");

            EventEnvelope01<TaskCreatedPayload01> env1 = new EventEnvelope01<>(
                    UUID.randomUUID(),           // eventId — один раз на событие
                    "task.created",
                    1,
                    Instant.now(),
                    "trace-1",
                    "task-tracker",
                    first);

            EventEnvelope01<TaskCreatedPayload01> env2 = new EventEnvelope01<>(
                    UUID.randomUUID(),
                    "task.created",
                    1,
                    Instant.now(),
                    "trace-2",
                    "task-tracker",
                    second);

            send(kafka, env1);
            send(kafka, env2);

            kafka.flush();
        };
    }

    private static void send(KafkaTemplate<String, Object> kafka,
                             EventEnvelope01<TaskCreatedPayload01> env) throws Exception {
        SendResult<String, Object> result = kafka
                .send("task.created", String.valueOf(env.payload().taskId()), env)
                .get(5, TimeUnit.SECONDS);

        var md = result.getRecordMetadata();
        System.out.printf("отправлено eventId=%s -> %s-%d offset %d%n",
                env.eventId(), md.topic(), md.partition(), md.offset());
    }
}
