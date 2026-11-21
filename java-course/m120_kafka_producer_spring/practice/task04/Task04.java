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

import java.time.Instant;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }

    @Bean
    ApplicationRunner runner(TaskEventPublisher04 publisher) {
        return args -> {
            // три события одной задачи 42 — должны попасть в одну партицию, порядок сохранён
            publisher.publish(42L, new TaskStatusChanged04(
                    42L, "NEW", "IN_PROGRESS", Instant.now()));
            publisher.publish(42L, new TaskStatusChanged04(
                    42L, "IN_PROGRESS", "DONE", Instant.now()));

            // событие задачи 7 — другая партиция (другой хеш ключа)
            publisher.publish(7L, new TaskStatusChanged04(
                    7L, "NEW", "IN_PROGRESS", Instant.now()));

            // событие без ключа — sticky-партиционер, партиция непредсказуема
            publisher.publish(null, new TaskStatusChanged04(
                    99L, "NEW", "DONE", Instant.now()));

            publisher.flush();

            // ВЫВОД:
            // - Задача 42: оба события ушли в ОДНУ партицию (hash("42") % 6 — детерминированно),
            //   порядок NEW->IN_PROGRESS->DONE сохранён, потому что Kafka гарантирует порядок
            //   только внутри партиции.
            // - Задача 7: другой ключ -> другой хеш -> скорее всего другая партиция.
            // - Без ключа: sticky-партиционер выбирает партицию для батча; при одном сообщении
            //   партиция может быть любой. Порядок без ключа не гарантирован.
        };
    }
}
