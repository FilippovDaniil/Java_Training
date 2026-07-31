package m124_kafka_event_patterns.practice.task01;

/**
 * Задача 01 — Модуль 124: outbox — событие и данные в одной транзакции БД
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-data-jpa, com.h2database:h2,
 *   spring-kafka (для следующей задачи), jackson-databind. Запуск в IDE (▶ main).
 *   Брокер для ЭТОЙ задачи не нужен — в ней ничего не отправляется, только пишется в БД.
 *
 * ФАЙЛЫ ЗАДАЧИ: Task01.java (точка входа), TaskEntity01.java (бизнес-сущность),
 *               OutboxMessage01.java (сообщение outbox), OutboxRepository01.java,
 *               TaskRepository01.java, TaskService01.java (транзакция), TaskController01.java.
 *
 * ЗАДАНИЕ:
 *   1) OutboxMessage01 — сущность с полями: id (UUID, PK — он же eventId), aggregateId (String),
 *      topic, type, payload (String с JSON), occurredAt (Instant), sentAt (Instant, nullable),
 *      attempts (int).
 *   2) TaskService01.createTask(title, authorId) с @Transactional:
 *        - сохранить TaskEntity01;
 *        - сериализовать событие TaskCreated в JSON (ObjectMapper);
 *        - сохранить OutboxMessage01 (topic = "task.created", aggregateId = id задачи, sentAt = null).
 *      Никаких обращений к Kafka внутри транзакции!
 *   3) TaskController01: POST /api/tasks -> 201; GET /api/outbox -> список неотправленных.
 *   4) Эксперимент: добавьте в конец createTask искусственное исключение и убедитесь,
 *      что откатились ОБЕ вставки (и задача, и outbox) — атомарность подтверждена.
 *
 * ПРОВЕРКА:
 *   curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" \
 *        -d '{"title":"Написать отчёт","authorId":10}'
 *   curl http://localhost:8080/api/outbox      # видим одну неотправленную запись
 *   (H2-консоль: spring.h2.console.enabled=true -> http://localhost:8080/h2-console)
 *
 * ОЖИДАЕМЫЙ ИТОГ: бизнес-данные и событие всегда фиксируются вместе. Событие ещё никуда не
 *   отправлено — это работа релэя (задача 02).
 *
 * ЦЕЛЬ: закрыть проблему двойной записи там, где она возникает — в транзакции.
 *
 * ВАЖНО: id сообщения outbox = eventId события. Он попадёт в Kafka и будет использован
 *   потребителем для дедупликации (модуль 123). Генерировать его при отправке — ошибка:
 *   при повторной отправке получится «новое» событие.
 *
 * ПОДСКАЗКА: payload держите строкой JSON (или jsonb в PostgreSQL) — outbox не должен зависеть
 *   от Java-классов событий, иначе рефакторинг сломает уже накопленные записи.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
