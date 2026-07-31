package m123_kafka_reliability.practice.task01;

/**
 * Задача 01 — Модуль 123: ретраи контейнера (DefaultErrorHandler + FixedBackOff)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task01.java (точка входа), ErrorHandlerConfig01.java (обработчик ошибок),
 *               FlakyListener01.java (слушатель, который иногда падает).
 *
 * ЗАДАНИЕ:
 *   1) ErrorHandlerConfig01: бин DefaultErrorHandler с FixedBackOff(1000L, 3) — 3 повтора по 1 сек.
 *   2) FlakyListener01: считать попытки по каждому сообщению; при значении, содержащем "flaky",
 *      падать первые 2 раза и успешно обработать с 3-й попытки; печатать номер попытки.
 *   3) Отправьте CLI-продюсером сообщение "flaky-1" в task.created и проследите вывод.
 *   4) Отправьте "always-bad" (падает всегда) — убедитесь, что после исчерпания попыток
 *      контейнер логирует ошибку, коммитит офсет и продолжает работу (не встаёт навсегда).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   попытка 1 для flaky-1 -> падаю
 *   попытка 2 для flaky-1 -> падаю
 *   попытка 3 для flaky-1 -> успех
 *   ... для always-bad: 3 попытки, затем ERROR от контейнера и переход к следующим записям
 *
 * ЦЕЛЬ: увидеть, что временный сбой лечится повторами автоматически и без потери события.
 *
 * ВАЖНО: повторы блокируют партицию — следующие записи ждут. Общее время повторов
 *   (интервал × попытки + время обработки) должно быть заметно меньше max.poll.interval.ms (5 мин),
 *   иначе группа уйдёт в перебалансировку и записи начнут обрабатываться заново по кругу.
 *
 * ПОДСКАЗКА: бин DefaultErrorHandler (тип CommonErrorHandler) Boot подхватывает автоматически
 *   в контейнер-фабрику по умолчанию — отдельная регистрация не нужна.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
