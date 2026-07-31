package m120_kafka_producer_spring.practice.task04;

/**
 * Задача 04 — Модуль 120: ключ, партиция, порядок — проверяем метаданными отправки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task04.java (точка входа), TaskEventPublisher04.java (сервис отправки),
 *               TaskStatusChanged04.java (событие).
 *
 * ЗАДАНИЕ:
 *   1) Допишите TaskStatusChanged04 (id, oldStatus, newStatus, occurredAt).
 *   2) В TaskEventPublisher04 реализуйте publish(...): отправка в "task.status.changed"
 *      с ключом = String.valueOf(taskId) и печатью партиции/офсета в whenComplete.
 *   3) В runner(...) отправьте:
 *        - три события ОДНОЙ задачи 42: NEW -> IN_PROGRESS -> DONE;
 *        - одно событие задачи 7;
 *        - одно событие БЕЗ ключа (ключ null) — сравните партицию.
 *   4) Соберите вывод и ответьте на вопросы в комментарии-выводе внизу runner.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   42: NEW -> IN_PROGRESS  ушло в partition 3, offset 12
 *   42: IN_PROGRESS -> DONE ушло в partition 3, offset 13     ← та же партиция, порядок сохранён
 *   7:  NEW -> IN_PROGRESS  ушло в partition 0, offset 4
 *   без ключа               ушло в partition 5, offset 1
 *
 * ЦЕЛЬ: увидеть своими глазами, что ключ детерминированно определяет партицию, а значит и порядок.
 *
 * ВАЖНО: партиция вычисляется как hash(ключ) % число_партиций — при изменении числа партиций
 *   ключ может «переехать» в другую партицию, и старый порядок для него не сохранится.
 *
 * ПОДСКАЗКА: RecordMetadata.partition() — в SendResult; при ключе null Kafka использует
 *   sticky-распределение (записи батча идут в одну партицию, следующий батч — в другую).
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }

    @Bean
    ApplicationRunner runner(TaskEventPublisher04 publisher) {
        return args -> {
            // TODO 1: три события задачи 42 (NEW -> IN_PROGRESS -> DONE)
            // TODO 2: одно событие задачи 7
            // TODO 3: одно событие с ключом null
            // TODO 4: вывод — в какие партиции ушли записи и почему
        };
    }
}
