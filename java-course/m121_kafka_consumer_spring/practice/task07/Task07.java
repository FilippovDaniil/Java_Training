package m121_kafka_consumer_spring.practice.task07;

/**
 * Задача 07 — Модуль 121: МИНИ-ПРОЕКТ «Сервис уведомлений на событиях Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-kafka, jackson-databind. Нужен брокер;
 * события публикует задача 07 модуля 120. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   Task07.java                  — точка входа
 *   TaskCreated07.java           — событие «создана»
 *   TaskStatusChanged07.java     — событие «статус изменён»
 *   NotificationService07.java   — бизнес-логика уведомлений (идемпотентная)
 *   TaskEventsListener07.java    — слушатели двух топиков с ручным коммитом
 *   ListenerAdminController07.java — REST: пауза/возобновление слушателя
 *
 * ЦЕЛЬ: собрать полноценного потребителя: две подписки, ручной коммит, идемпотентная
 *       обработка, управление слушателем в рантайме. Капстоун модуля.
 *
 * ЗАДАНИЕ:
 *   1) TaskCreated07 / TaskStatusChanged07 — контракты, совпадающие с продюсером модуля 120.
 *   2) NotificationService07:
 *        - notifyCreated(event) / notifyStatusChanged(event) — вывод «письма» в консоль;
 *        - идемпотентность: повторно пришедшее событие (тот же ключ дедупликации) не должно
 *          порождать второе уведомление.
 *   3) TaskEventsListener07:
 *        - слушатель task.created, группа "notifier", id = "notifier-created", concurrency = "3";
 *        - слушатель task.status.changed, группа "notifier", id = "notifier-status";
 *        - ручной коммит: ack.acknowledge() ПОСЛЕ успешной обработки;
 *        - уведомлять только о переходе в DONE (остальные переходы игнорировать).
 *   4) ListenerAdminController07: POST /api/listeners/{id}/pause и .../resume через
 *      KafkaListenerEndpointRegistry.
 *
 * ПРОВЕРКА (end-to-end):
 *   1) поднять брокер (модуль 119, задача 07), запустить продюсера (модуль 120, задача 07)
 *      и этот сервис;
 *   2) curl -X POST http://localhost:8080/api/tasks -d '{"title":"Отчёт","authorId":10}' \
 *          -H "Content-Type: application/json"        -> в консоли уведомление о создании;
 *   3) curl -X PATCH http://localhost:8080/api/tasks/1 -d '{"status":"DONE"}' \
 *          -H "Content-Type: application/json"        -> уведомление о завершении;
 *   4) POST /api/listeners/notifier-created/pause -> отправить ещё событие: уведомления нет,
 *      LAG растёт (kafka-consumer-groups.sh --describe --group notifier);
 *   5) POST /api/listeners/notifier-created/resume -> накопленное обработано, LAG вернулся к 0.
 *
 * ОЖИДАЕМЫЙ ИТОГ: сервис уведомлений полностью развязан с Task Tracker: они общаются только
 *   событиями, каждый масштабируется и падает независимо, ничего не теряется.
 *
 * ВАЖНО: два слушателя в ОДНОЙ группе "notifier" читают РАЗНЫЕ топики — это нормально,
 *   офсеты хранятся по (группа, топик, партиция). А вот два слушателя одного топика в одной
 *   группе поделили бы партиции между собой.
 *
 * ПОДСКАЗКА: соедините задачи 03 (JSON), 04 (ручной ack), 05 (concurrency) этого модуля;
 *   дедупликацию сделайте по (topic, partition, offset) или по идентификатору события —
 *   в модуле 123 разберём, почему второй способ правильнее.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
