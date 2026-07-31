package m123_kafka_reliability.practice.task06;

/**
 * Задача 06 — Модуль 123: транзакции Kafka (executeInTransaction, read_committed)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task06.java (точка входа), TransactionalPublisher06.java (две отправки в
 *               одной транзакции), IsolationListener06.java (два консьюмера с разной изоляцией).
 *
 * ЗАДАНИЕ:
 *   1) Включите транзакционного продюсера:
 *        spring.kafka.producer.transaction-id-prefix: tasktracker-tx-
 *      и настройте потребителя на чтение только зафиксированных записей:
 *        spring.kafka.consumer.isolation-level: read_committed
 *   2) TransactionalPublisher06.publishBoth(...): в одной транзакции отправить событие
 *      в "task.created" И запись в "task.audit"; вернуть true (коммит).
 *   3) TransactionalPublisher06.publishAndRollback(...): отправить те же две записи, затем
 *      бросить исключение внутри транзакции — обе отправки должны быть отменены.
 *   4) IsolationListener06: слушатель с read_committed НЕ должен увидеть откатанные записи;
 *      второй слушатель (read_uncommitted) увидит их. Сравните вывод.
 *   5) Ответьте в комментарии: почему транзакция Kafka НЕ решает проблему «БД + брокер».
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   [committed] task.created: событие 1
 *   [committed] task.audit:   запись 1
 *   (после publishAndRollback)
 *   [uncommitted] task.created: событие 2      ← видно только с read_uncommitted
 *   [committed]   — ничего не получено
 *
 * ЦЕЛЬ: понять область действия транзакций Kafka: атомарность нескольких отправок и коммита
 *       офсетов ВНУТРИ Kafka — и не более того.
 *
 * ВАЖНО: транзакции требуют, чтобы у каждого экземпляра приложения был свой transactional.id
 *   (Spring строит его из префикса + идентификатора), а у топиков — min.insync.replicas,
 *   позволяющий подтвердить запись. При RF=1 в dev это работает, в проде нужен RF≥3.
 *
 * ПОДСКАЗКА: транзакционный продюсер пишет в лог служебные маркеры коммита/аборта — поэтому
 *   офсеты в топике «прыгают» (не строго +1 на сообщение). Это нормально.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task06 {

    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
