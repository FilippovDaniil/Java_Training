package m122_kafka_serialization_contracts.practice.task01;

import java.time.Instant;
import java.util.UUID;

/**
 * Универсальный конверт события: одинаков для всех типов событий системы.
 *
 * TODO: параметризуйте record типом payload и объявите поля:
 *   UUID eventId       — уникальный идентификатор события (дедупликация);
 *   String type        — тип события ("task.created");
 *   int version        — версия схемы payload;
 *   Instant occurredAt — когда факт произошёл (UTC);
 *   String traceId     — сквозная трассировка;
 *   String source      — сервис-источник;
 *   T payload          — бизнес-данные.
 *
 * ПОЧЕМУ КОНВЕРТ ОДИН НА ВСЕ СОБЫТИЯ: инфраструктурный код (логирование, дедупликация,
 *   трассировка, DLQ-диагностика) пишется один раз и работает для любого типа события.
 *
 * ПОДСКАЗКА (десериализация generic-типа): Jackson не знает, чем является T во время чтения.
 *   У потребителя используют конкретный тип (EventEnvelope01<TaskCreatedPayload01>) через
 *   JavaType/TypeReference либо разбирают payload вторым шагом по полю type.
 */
public record EventEnvelope01(UUID eventId) {
}
