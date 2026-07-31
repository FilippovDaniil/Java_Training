package m121_kafka_consumer_spring.practice.task06;

/**
 * Задача 06 — Модуль 121: батчевое чтение и защита от «отравленного» сообщения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task06.java (точка входа), BatchListener06.java (батчевый слушатель),
 *               TaskCreated06.java (событие).
 *
 * ЗАДАНИЕ:
 *   1) Включите батчевый режим и ограничьте размер партии:
 *        spring.kafka.listener.type: batch
 *        spring.kafka.consumer.max-poll-records: 100
 *   2) В BatchListener06 реализуйте onBatch(List<TaskCreated06> events, Acknowledgment ack):
 *        - напечатать размер батча и диапазон id;
 *        - «сохранить» батч одной операцией (эмуляция: одна строка вывода);
 *        - ack.acknowledge().
 *   3) Защитите слушателя от битого сообщения:
 *        spring.kafka.consumer.value-deserializer:
 *            org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
 *        spring.kafka.consumer.properties.spring.deserializer.value.delegate.class:
 *            org.springframework.kafka.support.serializer.JsonDeserializer
 *   4) Эксперимент: отправьте CLI-продюсером строку «не-json» в топик и сравните поведение
 *      ДО и ПОСЛЕ подключения ErrorHandlingDeserializer. Зафиксируйте наблюдение в комментарии.
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   без обёртки  — SerializationException в цикле, офсет не двигается, обработка встала (poison pill);
 *   с обёрткой   — сбойная запись уходит в обработчик ошибок (далее — DLQ, модуль 123),
 *                  остальные события батча обрабатываются, офсет двигается.
 *
 * ЦЕЛЬ: научиться читать пачками (пропускная способность) и не вставать из-за одной битой записи.
 *
 * ВАЖНО: при ошибке обработки повторяется ВЕСЬ батч. Если нужно «повторить только сбойную
 *   запись» — бросайте BatchListenerFailedException с индексом записи (модуль 123).
 *
 * ПОДСКАЗКА: размер батча в выводе почти никогда не равен max-poll-records — сколько успело
 *   накопиться в брокере к моменту poll'а, столько и придёт.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task06 {

    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
