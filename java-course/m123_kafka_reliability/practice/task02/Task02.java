package m123_kafka_reliability.practice.task02;

/**
 * Задача 02 — Модуль 123: экспоненциальный backoff и не-повторяемые исключения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task02.java (точка входа), BackOffConfig02.java (настройка повторов),
 *               ValidatingListener02.java (два вида ошибок).
 *
 * ЗАДАНИЕ:
 *   1) BackOffConfig02: DefaultErrorHandler с ExponentialBackOffWithMaxRetries(3):
 *        initialInterval = 1000, multiplier = 2.0, maxInterval = 10_000  -> паузы 1с, 2с, 4с.
 *   2) Отметьте бизнес-ошибки как НЕ повторяемые:
 *        handler.addNotRetryableExceptions(IllegalArgumentException.class);
 *   3) ValidatingListener02:
 *        - пустой/некорректный payload -> IllegalArgumentException (валидация: повторять нечего);
 *        - значение содержит "db-down" -> RuntimeException (временный сбой: повторять нужно).
 *   4) Отправьте оба вида сообщений и сравните поведение по времени и числу попыток.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   для "db-down": попытки 1..4 с растущими паузами (1с, 2с, 4с), затем отказ;
 *   для некорректного payload: РОВНО одна попытка — повторов нет, ошибка сразу финальная.
 *
 * ЦЕЛЬ: научиться отличать временную ошибку от постоянной и не тратить ресурсы на бессмысленные
 *       повторы (а заодно не забивать лог одинаковыми ошибками).
 *
 * ВАЖНО: экспоненциальный backoff обязателен, когда причина сбоя — перегруженный внешний сервис:
 *   постоянные повторы с фиксированным интервалом добивают его окончательно.
 *
 * ПОДСКАЗКА: список не-повторяемых исключений — это ваш «контракт ошибок»: ошибки валидации,
 *   нарушения бизнес-правил, отсутствие сущности. Всё, что зависит от инфраструктуры, повторяемо.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task02 {

    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
