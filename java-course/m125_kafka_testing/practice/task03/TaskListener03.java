package m125_kafka_testing.practice.task03;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель с наблюдаемым состоянием — то, что проверяет тест.
 *
 * TODO 1: сохранять полученные значения в received (список) — по нему тест проверит и число,
 *         и порядок.
 * TODO 2: метод received() — размер; values() — неизменяемая копия списка.
 * TODO 3: id = "task-listener" в @KafkaListener — нужен для ContainerTestUtils.waitForAssignment.
 *
 * ПОЧЕМУ CopyOnWriteArrayList: слушатель пишет из своего потока, тест читает из своего.
 *   Обычный ArrayList дал бы гонку и «мигающие» падения.
 */
@Component
public class TaskListener03 {

    private final List<String> values = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "task.created", id = "task-listener", groupId = "test-group")
    public void on(String value) {
        // TODO 1
    }

    public int received() {
        // TODO 2
        return values.size();
    }

    public List<String> values() {
        return List.copyOf(values);
    }
}
