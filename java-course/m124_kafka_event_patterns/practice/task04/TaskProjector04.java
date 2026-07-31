package m124_kafka_event_patterns.practice.task04;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Проектор: превращает поток событий в таблицу для чтения.
 *
 * TODO 1: onCreated (топик "task.created", группа "projector"):
 *           - найти TaskView04 по taskId или создать новую;
 *           - заполнить title/authorId/status/updatedAt, обновить lastEventVersion;
 *           - сохранить; ack.acknowledge().
 * TODO 2: onStatusChanged (топик "task.status.changed", группа "projector"):
 *           - найти запись; если её нет — создать «частичную» (событие могло прийти раньше
 *             события создания: разные топики -> порядок между ними НЕ гарантирован);
 *           - обновить статус только если версия события новее lastEventVersion.
 * TODO 3: @Transactional на методах — запись в проекцию атомарна.
 * TODO 4 (проверка идемпотентности): повторное событие с той же версией не должно менять данные.
 *
 * ВАЖНЫЙ АРХИТЕКТУРНЫЙ НЮАНС: порядок гарантирован только внутри партиции ОДНОГО топика.
 *   Если события создания и смены статуса лежат в разных топиках, «статус изменён» может
 *   прийти первым. Отсюда два практических решения: (1) держать поток событий одной сущности
 *   в одном топике (модуль 122, задача 04) либо (2) писать проектор так, чтобы он выдерживал
 *   любой порядок (версии + частичные записи).
 */
@Component
public class TaskProjector04 {

    private final TaskViewRepository04 views;

    public TaskProjector04(TaskViewRepository04 views) {
        this.views = views;
    }

    @KafkaListener(topics = "task.created", groupId = "projector")
    @Transactional
    public void onCreated(String payload, Acknowledgment ack) {
        // TODO 1
    }

    @KafkaListener(topics = "task.status.changed", groupId = "projector")
    @Transactional
    public void onStatusChanged(String payload, Acknowledgment ack) {
        // TODO 2
    }
}
