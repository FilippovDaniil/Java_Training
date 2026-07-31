package m124_kafka_event_patterns.practice.task06;

/**
 * Задача 06 — Модуль 124: saga (хореография) с компенсацией
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task06.java (точка входа), SagaTopics06.java (топики процесса),
 *               QuotaService06.java (шаг 1: бронирование квоты), TaskCreationService06.java
 *               (шаг 2: создание задачи + компенсация), SagaStartController06.java (запуск).
 *
 * СЦЕНАРИЙ (процесс из двух шагов через два сервиса):
 *   1) пользователь просит создать задачу -> событие "quota.reserve.requested";
 *   2) QuotaService06 бронирует квоту автора:
 *        успех -> "quota.reserved";  нет квоты -> "quota.rejected";
 *   3) TaskCreationService06 на "quota.reserved" создаёт задачу:
 *        успех -> "task.created";
 *        сбой  -> компенсирующее событие "quota.release.requested";
 *   4) QuotaService06 на "quota.release.requested" возвращает квоту (компенсация).
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте оба сервиса как @KafkaListener'ы, публикующие следующее событие процесса.
 *   2) Каждый шаг идемпотентен: повторное событие с тем же sagaId не бронирует квоту дважды.
 *   3) Храните состояние саги (sagaId -> текущий шаг) в памяти и печатайте переходы.
 *   4) Эксперимент A: автор без квоты -> процесс останавливается на "quota.rejected".
 *   5) Эксперимент B: заставьте создание задачи падать -> убедитесь, что квота возвращена
 *      компенсацией, а состояние саги стало COMPENSATED.
 *   6) Ответьте в комментарии: что изменится, если шагов станет пять (подсказка: оркестрация).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   saga 7: RESERVE_REQUESTED -> QUOTA_RESERVED -> TASK_CREATED (успех)
 *   saga 8: RESERVE_REQUESTED -> QUOTA_RESERVED -> TASK_FAILED -> QUOTA_RELEASED (компенсация)
 *   saga 9: RESERVE_REQUESTED -> QUOTA_REJECTED (остановлено на первом шаге)
 *
 * ЦЕЛЬ: увидеть, что распределённый процесс без общей транзакции строится из идемпотентных
 *       шагов и компенсаций, а не из «двухфазного коммита».
 *
 * ВАЖНО: компенсация — не «rollback». Это отдельное бизнес-действие («вернуть квоту»,
 *   «сделать возврат платежа»), которое тоже может упасть и тоже должно быть идемпотентным.
 *
 * ПОДСКАЗКА: без хранения состояния саги система после рестарта не знает, на каком шаге
 *   остановилась. В проде состояние саги — таблица в БД, а к каждому шагу добавляется таймаут:
 *   «нет ответа за N минут -> компенсировать».
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task06 {

    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
