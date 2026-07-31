package m120_kafka_producer_spring.practice.task05;

/**
 * Задача 05 — Модуль 120: результат отправки — async, sync и ошибки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task05.java (точка входа), ReliablePublisher05.java (три способа отправки).
 *
 * ЗАДАНИЕ:
 *   Реализуйте в ReliablePublisher05 три способа и сравните их поведение:
 *     1) sendAsync(...)   — send(...).whenComplete(...): результат приходит в другом потоке,
 *                            метод возвращается сразу;
 *     2) sendSync(...)    — send(...).get(5, TimeUnit.SECONDS): ждём подтверждения,
 *                            оборачиваем InterruptedException/ExecutionException/TimeoutException;
 *     3) sendToMissing(...) — отправка в топик "no.such.topic" при выключенном автосоздании:
 *                            получите и напечатайте ошибку (TimeoutException: Topic not present
 *                            in metadata), не «проглатывая» её.
 *   В runner(...) вызовите все три и напечатайте, в каком порядке появились строки вывода.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   async: метод вернулся
 *   async: подтверждено partition 2 offset 7
 *   sync: подтверждено partition 4 offset 3   (после ожидания)
 *   ошибка отправки в no.such.topic: Topic no.such.topic not present in metadata after 60000 ms
 *
 * ЦЕЛЬ: понять цену синхронной отправки и то, что необработанный сбой send() = молча потерянное событие.
 *
 * ВАЖНО: .get() БЕЗ таймаута в потоке обработки HTTP-запроса — прямой путь к «залипшему» сервису.
 *   В контроллере отправляйте асинхронно, а ошибку логируйте/сохраняйте (модуль 124 — outbox).
 *
 * ПОДСКАЗКА: чтобы автосоздание топиков не мешало эксперименту, проверьте на брокере
 *   auto.create.topics.enable (в проде — false) или используйте заведомо запрещённое имя.
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Task05 {

    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }

    @Bean
    ApplicationRunner runner(ReliablePublisher05 publisher) {
        return args -> {
            // TODO 1: publisher.sendAsync("task.created", "task-1", "async");
            // TODO 2: publisher.sendSync("task.created", "task-2", "sync");
            // TODO 3: publisher.sendToMissing("no.such.topic", "task-3", "fail");
            // TODO 4: вывод — в каком порядке пришли строки и почему
        };
    }
}
