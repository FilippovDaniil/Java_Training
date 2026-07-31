package m125_kafka_testing.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Тестируемое приложение: контур outbox -> релэй -> проекция (модуль 124, задача 07).
 *
 * TODO: подключите к этому контексту компоненты контура (команду, релэй, проектор, репозитории)
 *   — либо перенесите их сюда, либо импортируйте пакет задачи 07 модуля 124 через
 *   @Import/@ComponentScan. Тесты набора работают именно с этим контекстом.
 *
 * СОВЕТ: для теста релэя удобно уменьшить интервал: spring properties
 *   "app.outbox.relay.delay=100" и @Scheduled(fixedDelayString = "${app.outbox.relay.delay}").
 */
@SpringBootApplication
@EnableScheduling
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
