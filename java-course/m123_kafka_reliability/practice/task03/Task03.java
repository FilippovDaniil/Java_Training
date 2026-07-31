package m123_kafka_reliability.practice.task03;

/**
 * Задача 03 — Модуль 123: Dead Letter Topic (DeadLetterPublishingRecoverer + разбор DLT)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task03.java (точка входа), DlqConfig03.java (recoverer + обработчик),
 *               MainListener03.java (основной слушатель), DltListener03.java (слушатель DLT).
 *
 * ЗАДАНИЕ:
 *   1) DlqConfig03: DeadLetterPublishingRecoverer, публикующий сбойную запись в
 *      "<исходный топик>.DLT" (партиция -1 — выбрать по ключу), и DefaultErrorHandler
 *      с этим recoverer + FixedBackOff(500L, 2).
 *   2) MainListener03: падать на сообщениях, содержащих "bad".
 *   3) DltListener03: читать "task.created.DLT" и печатать диагностику из заголовков:
 *        kafka_dlt-original-topic, kafka_dlt-original-partition, kafka_dlt-original-offset,
 *        kafka_dlt-exception-fqcn, kafka_dlt-exception-message.
 *   4) Отправьте "bad-1" и убедитесь: 3 попытки в основном слушателе -> запись в DLT
 *      -> DLT-слушатель напечатал причину. Проверьте топик:
 *        kafka-topics.sh --bootstrap-server localhost:9092 --list | grep DLT
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   [main] обработка bad-1 -> падаю (x3)
 *   [DLT] запись из task.created-2 offset 41: RuntimeException: не могу обработать bad-1
 *
 * ЦЕЛЬ: не терять сбойные события и иметь всю диагностику для разбора инцидента.
 *
 * ВАЖНО: DLT сам по себе ничего не решает — нужен АЛЕРТ на непустой DLT и процедура повторной
 *   обработки. Молчащая DLQ = тихо потерянные бизнес-события (в задаче 07 добавим reprocessing).
 *
 * ПОДСКАЗКА: DLT-топик создаётся автоматически, если на брокере включено автосоздание;
 *   иначе создайте его заранее (NewTopic бином, модуль 120) — иначе recoverer сам упадёт.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task03 {

    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
