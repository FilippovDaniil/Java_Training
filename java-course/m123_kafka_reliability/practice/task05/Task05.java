package m123_kafka_reliability.practice.task05;

/**
 * Задача 05 — Модуль 123: неблокирующие ретраи (@RetryableTopic + @DltHandler)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер (с автосозданием
 * топиков или заранее созданными retry-топиками). Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task05.java (точка входа), RetryableTopicListener05.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте слушатель аннотацией:
 *        @RetryableTopic(attempts = "4",
 *                        backoff = @Backoff(delay = 1000, multiplier = 2.0),
 *                        autoCreateTopics = "true",
 *                        dltStrategy = DltStrategy.FAIL_ON_ERROR)
 *   2) Реализуйте обработку: падать на сообщениях с "bad", печатать топик, из которого пришла
 *      запись (заголовок KafkaHeaders.RECEIVED_TOPIC) — так видно движение по retry-топикам.
 *   3) Добавьте @DltHandler-метод: печатать финальный отказ и причину.
 *   4) Запустите, отправьте "bad-1" и "good-1" ОДНОВРЕМЕННО в одну партицию. Проверьте:
 *        - "good-1" обработан сразу, не дожидаясь повторов "bad-1" (основной поток не блокирован);
 *        - "bad-1" последовательно прошёл task.created-retry-0, -retry-1, -retry-2 и попал в -dlt.
 *   5) Посмотрите созданные топики: kafka-topics.sh --list | grep task.created
 *   6) Ответьте в комментарии: что стало с ПОРЯДКОМ обработки и когда это неприемлемо.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [task.created] good-1 -> обработано
 *   [task.created] bad-1 -> падаю
 *   [task.created-retry-0] bad-1 -> падаю
 *   [task.created-retry-1] bad-1 -> падаю
 *   [DLT] bad-1: RuntimeException: не могу обработать
 *
 * ЦЕЛЬ: понять второй способ ретраев и осознанно выбирать между сохранением порядка и
 *       непрерывной пропускной способностью.
 *
 * ВАЖНО: неблокирующие ретраи НАРУШАЮТ порядок событий: «плохая» запись уходит в отдельный
 *   топик, а следующие за ней обрабатываются раньше. Для потока «статусы одной задачи» это
 *   недопустимо (DONE может примениться раньше IN_PROGRESS) — там нужен blocking retry.
 *
 * ПОДСКАЗКА: retry-топики и dlt создаёт Spring (autoCreateTopics), но на проде их обычно заводят
 *   заранее вместе с основным топиком — с тем же числом партиций.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task05 {

    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
