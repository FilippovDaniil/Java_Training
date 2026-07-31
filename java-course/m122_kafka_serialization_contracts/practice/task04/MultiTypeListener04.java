package m122_kafka_serialization_contracts.practice.task04;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель одного топика с несколькими типами событий.
 *
 * TODO 1: onCreated(TaskCreated04 event) — «создана задача {taskId}: {title}».
 * TODO 2: onStatusChanged(TaskStatusChanged04 event) — «задача {taskId}: {old} -> {new}».
 * TODO 3: добавить метод-заглушку с @KafkaHandler(isDefault = true) и параметром Object —
 *         печатать «неизвестный тип события: ...», не падая.
 *
 * КАК ЭТО РАБОТАЕТ: @KafkaListener на КЛАССЕ + @KafkaHandler на методах = Spring выбирает
 *   метод по типу десериализованного payload (тип берётся из type.mapping/__TypeId__).
 *
 * ВАЖНО: без default-обработчика неизвестный тип приводит к исключению на каждой попытке —
 *   классический источник зацикливания слушателя (poison pill, модуль 121).
 */
@Component
@KafkaListener(topics = "task.events", groupId = "multi-type")
public class MultiTypeListener04 {

    @KafkaHandler
    public void onCreated(TaskCreated04 event) {
        // TODO 1
    }

    @KafkaHandler
    public void onStatusChanged(TaskStatusChanged04 event) {
        // TODO 2
    }

    // TODO 3: @KafkaHandler(isDefault = true) public void onUnknown(Object payload) { ... }
}
