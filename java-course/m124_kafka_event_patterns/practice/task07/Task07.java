package m124_kafka_event_patterns.practice.task07;

/**
 * Задача 07 — Модуль 124: МИНИ-ПРОЕКТ «Task Tracker на событиях: outbox → релэй → проекция»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-data-jpa, h2,
 *   spring-kafka, jackson-databind. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   Task07.java              — точка входа (@EnableScheduling)
 *   TaskEntity07.java        — бизнес-сущность
 *   OutboxMessage07.java     — запись outbox
 *   TaskRepository07.java / OutboxRepository07.java / TaskViewRepository07.java
 *   TaskCommandService07.java— команды: создать/сменить статус (БД + outbox в одной транзакции)
 *   OutboxRelay07.java       — релэй outbox -> Kafka
 *   TaskProjector07.java     — проекция task_view из событий
 *   TaskApiController07.java — REST: команды (write) и запросы к проекции (read)
 *
 * ЦЕЛЬ: собрать полный событийный контур одного сервиса — от команды до модели чтения, без
 *       потерь и фантомных событий, с возможностью пересобрать проекцию. Капстоун модуля.
 *
 * АРХИТЕКТУРА, КОТОРУЮ СОБИРАЕМ:
 *   POST /api/tasks                 GET /api/tasks?status=DONE
 *        |                                    ▲
 *        ▼                                    |
 *   TaskCommandService07  --(одна транзакция)--+--> tasks + outbox
 *        |                                            |
 *        |                                   OutboxRelay07 (@Scheduled)
 *        |                                            ▼
 *        |                                    Kafka: task.created / task.status.changed
 *        |                                            |
 *        +-------- TaskProjector07 (@KafkaListener) ---+--> task_view (модель чтения)
 *
 * ЗАДАНИЕ:
 *   1) Команды (write-сторона): в одной транзакции сохранять сущность и событие в outbox
 *      (eventId = id записи outbox, aggregateId = id задачи, payload = JSON события).
 *   2) Релэй: @Scheduled(fixedDelay = 500) отправляет неотправленные записи, помечает sentAt,
 *      считает попытки; порядок по occurredAt, ключ = aggregateId.
 *   3) Проектор: идемпотентный upsert в task_view; выдерживает повторную доставку и события,
 *      пришедшие не по порядку (проверка версии/времени события).
 *   4) Чтение: GET /api/tasks?status=... и GET /api/tasks/stats — только из проекции.
 *   5) Эндпоинт эксплуатации: GET /api/ops — {outboxUnsent, viewRows, lastRelayRun} для проверки
 *      «сходится ли система» (сколько событий ждёт отправки, сколько строк в проекции).
 *
 * ПРОВЕРКА (сценарии):
 *   1) создать 3 задачи -> в outbox 3 записи -> через ~0.5 с outboxUnsent = 0, в task_view 3 строки;
 *   2) остановить брокер, сменить статусы -> API отвечает 200, outboxUnsent растёт;
 *      поднять брокер -> релэй догоняет, проекция обновляется;
 *   3) сбросить офсеты группы projector в earliest и очистить task_view -> проекция полностью
 *      пересобирается из топиков;
 *   4) отправить повторно уже обработанное событие -> проекция не меняется (идемпотентность).
 *
 * ОЖИДАЕМЫЙ ИТОГ: сервис, у которого запись и чтение разделены, события гарантированно
 *   доходят до брокера, а модель чтения восстанавливается из лога. Это рабочий шаблон
 *   для продуктовых сервисов на Kafka.
 *
 * ВАЖНО: не заводите «одну таблицу на всё». Источник правды для команд — tasks; task_view —
 *   производная, которую можно удалить и пересобрать. Если проекцию нельзя пересобрать,
 *   она стала вторым источником правды — это архитектурная ошибка.
 *
 * ПОДСКАЗКА: соедините задачи 01 (outbox), 02 (релэй), 04 (проекция) и добавьте эксплуатационный
 *   эндпоинт. Тесты для этого контура напишем в модуле 125, эксплуатацию настроим в 126.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
