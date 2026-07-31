package m120_kafka_producer_spring.practice.task04;

import java.time.Instant;

/**
 * Событие «статус задачи изменился».
 *
 * TODO: поля record:
 *   long id            — задача (ключ сообщения);
 *   String oldStatus   — прежний статус;
 *   String newStatus   — новый статус;
 *   Instant occurredAt — время изменения.
 *
 * ПОДСКАЗКА: события «изменение состояния» полезно публиковать парой (было -> стало):
 *   подписчик может отреагировать только на нужный переход (например, NEW -> DONE).
 */
public record TaskStatusChanged04(long id) {
}
