package m21_saga_distributed_transactions.practice.task01;

/**
 * Задача 01 — Тема 21: Saga — последовательность шагов
 *
 * ЗАДАНИЕ:
 *   Реализуйте простейшую сагу — последовательное выполнение шагов:
 *     - интерфейс SagaStep (файл SagaStep.java): void execute();
 *     - ReserveStep ("резерв товара") и ChargeStep ("списание оплаты") — печатают
 *       своё действие;
 *     - SagaRunner (файл SagaRunner.java): run(List<SagaStep> steps) выполняет
 *       шаги по порядку.
 *   В main соберите сагу [Reserve, Charge] и запустите её.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   резерв товара
 *   списание оплаты
 *
 * ТРЕБОВАНИЯ:
 *   - шаги выполняются строго в порядке списка;
 *   - SagaRunner не знает конкретных шагов (только интерфейс SagaStep);
 *   - каждый шаг — отдельное локальное действие.
 *
 * ПОДСКАЗКА:
 *   Это «скелет» саги без компенсаций — их добавим в Task02. Пока просто
 *   последовательность локальных шагов.
 */

import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        // TODO: соберите List<SagaStep> и запустите через SagaRunner.run(...)
    }
}
