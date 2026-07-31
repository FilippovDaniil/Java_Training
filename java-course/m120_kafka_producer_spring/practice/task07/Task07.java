package m120_kafka_producer_spring.practice.task07;

/**
 * Задача 07 — Модуль 120: МИНИ-ПРОЕКТ «Task Tracker публикует события»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main), затем HTTP-запросы (curl/Postman).
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   Task07.java               — точка входа (@SpringBootApplication)
 *   KafkaTopicsConfig07.java  — топики бинами (NewTopic)
 *   TaskController07.java     — REST: создание задачи и смена статуса
 *   TaskService07.java        — бизнес-логика + публикация событий
 *   TaskEventPublisher07.java — единственное место, знающее про Kafka
 *   TaskCreated07.java        — событие «создана»
 *   TaskStatusChanged07.java  — событие «статус изменён»
 *
 * ЦЕЛЬ: превратить REST-сервис из части 3 курса в источник событий: каждое изменение задачи
 *       порождает событие в Kafka с правильным ключом, а бизнес-код не знает про брокер.
 *       Капстоун модуля — основа для консьюмера в модуле 121.
 *
 * ЗАДАНИЕ:
 *   1) KafkaTopicsConfig07: топики task.created и task.status.changed (6 партиций, 1 реплика).
 *   2) TaskCreated07 / TaskStatusChanged07: поля контракта (см. TODO в файлах).
 *   3) TaskEventPublisher07: publishCreated / publishStatusChanged — ключ = id задачи,
 *      обработка результата (лог ошибки, не «проглатывать»).
 *   4) TaskService07: create(...) и changeStatus(...) — сохранить в хранилище, затем опубликовать
 *      событие; вернуть DTO контроллеру.
 *   5) TaskController07: POST /api/tasks (201 Created), PATCH /api/tasks/{id} со статусом (200).
 *
 * ПРОВЕРКА (end-to-end):
 *   curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" \
 *        -d '{"title":"Написать отчёт","authorId":10}'
 *   curl -X PATCH http://localhost:8080/api/tasks/1 -H "Content-Type: application/json" \
 *        -d '{"status":"DONE"}'
 *   docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
 *       --topic task.created --from-beginning --property print.key=true
 *   docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
 *       --topic task.status.changed --from-beginning --property print.key=true
 *
 * ОЖИДАЕМЫЙ ИТОГ: на каждый REST-вызов в соответствующем топике появляется одно событие
 *   с ключом = id задачи; события одной задачи лежат в одной партиции по порядку.
 *
 * ВАЖНО: публикуйте события ПОСЛЕ фиксации изменения (в реальном проекте — после коммита
 *   транзакции БД: @TransactionalEventListener(phase = AFTER_COMMIT) или outbox из модуля 124).
 *   Иначе подписчики узнают о том, чего в БД не случилось.
 *
 * ПОДСКАЗКА: соедините задачи 02 (топики бинами), 03 (JSON-события), 04 (ключ),
 *   05 (обработка результата) и REST-контроллеры из модулей 69–72.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
