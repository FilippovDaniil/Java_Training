package m125_kafka_testing.practice.task05;

/**
 * Задача 05 — Модуль 125: тесты ретраев и DLQ (короткий BackOff, консьюмер на DLT)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-kafka-test, spring-boot-starter-test, awaitility.
 * Тест-класс без main. Брокер встроенный (@EmbeddedKafka).
 *
 * ФАЙЛЫ ЗАДАЧИ: RetryAndDltTest05.java (тест), Task05.java (тестовое приложение),
 *               FailingListener05.java (слушатель со счётчиком попыток),
 *               TestReliabilityConfig05.java (быстрый BackOff + DLQ для тестов).
 *
 * ЗАДАНИЕ:
 *   1) TestReliabilityConfig05: DefaultErrorHandler с FixedBackOff(50L, 2L) — 3 попытки почти без
 *      задержек — и DeadLetterPublishingRecoverer в "<топик>.DLT".
 *   2) retries_configured_number_of_times(): отправить сообщение, на котором слушатель падает;
 *      дождаться, что счётчик попыток стал 3 (Awaitility), а не 1 и не бесконечность.
 *   3) failed_event_lands_in_dlt(): тестовым консьюмером прочитать "task.created.DLT" и проверить:
 *        - значение совпадает с исходным;
 *        - заголовок kafka_dlt-exception-message содержит текст исключения;
 *        - заголовок kafka_dlt-original-topic = "task.created".
 *   4) not_retryable_exception_goes_straight_to_dlt(): для IllegalArgumentException (добавьте её
 *      в notRetryable) попыток должно быть РОВНО 1, а запись — сразу в DLT.
 *   5) successful_event_never_reaches_dlt(): нормальное сообщение обработано, в DLT ничего нет.
 *
 * ЦЕЛЬ: зафиксировать тестами политику надёжности из модуля 123 — иначе она «уплывёт» при
 *       первом же рефакторинге конфигурации.
 *
 * ВАЖНО: в тестовом профиле интервалы ретраев должны быть минимальными (50 мс). С боевым
 *   ExponentialBackOff (1с/2с/4с) один такой тест будет идти 7+ секунд, а набор — минуты.
 *   Значение backoff берите из свойств, чтобы прод и тест отличались только конфигурацией.
 *
 * ПОДСКАЗКА: число попыток удобно считать в самом слушателе (AtomicInteger) — это наблюдаемое
 *   состояние, по которому тест делает утверждения без чтения логов.
 */

import org.junit.jupiter.api.Test;

class RetryAndDltTest05 {

    @Test
    void retries_configured_number_of_times() {
        // TODO 2
    }

    @Test
    void failed_event_lands_in_dlt() {
        // TODO 3
    }

    @Test
    void not_retryable_exception_goes_straight_to_dlt() {
        // TODO 4
    }

    @Test
    void successful_event_never_reaches_dlt() {
        // TODO 5
    }
}
