package m124_kafka_event_patterns.practice.task07;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Read-сторона: строит task_view7 из событий.
 *
 * TODO 1: onCreated ("task.created", группа "projector") — upsert строки проекции.
 * TODO 2: onStatusChanged ("task.status.changed", группа "projector") — обновить статус,
 *         только если version события больше сохранённой (иначе игнорировать: повтор/устаревшее).
 * TODO 3: если события смены статуса пришли раньше события создания — создать частичную строку
 *         и дополнить её позже (порядок между РАЗНЫМИ топиками не гарантирован).
 * TODO 4: ручной ack после успешного сохранения; при исключении — не подтверждать.
 *
 * ПРОВЕРКА ПЕРЕСБОРКИ: очистите task_view7, сбросьте офсеты группы projector на earliest —
 *   проекция должна восстановиться из топиков полностью. Если не восстанавливается,
 *   значит в проекторе есть состояние, которого нет в событиях.
 */
@Component
public class TaskProjector07 {

    private final TaskViewRepository07 views;
    private final ObjectMapper objectMapper;

    public TaskProjector07(TaskViewRepository07 views, ObjectMapper objectMapper) {
        this.views = views;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "task.created", groupId = "projector")
    @Transactional
    public void onCreated(String payload, Acknowledgment ack) {
        // TODO 1 + TODO 4
    }

    @KafkaListener(topics = "task.status.changed", groupId = "projector")
    @Transactional
    public void onStatusChanged(String payload, Acknowledgment ack) {
        // TODO 2-4
    }
}
