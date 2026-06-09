/**
 * Задача 03 — Тема 21: Saga-оркестрация (откат при сбое)
 *
 * ЗАДАНИЕ:
 *   Оркестратор выполняет шаги по очереди, а при сбое компенсирует уже сделанные
 *   В ОБРАТНОМ порядке:
 *     - интерфейс SagaStep (файл SagaStep.java): execute(); compensate();
 *     - ReserveStep (execute "зарезервировано" / compensate "резерв снят");
 *       ChargeStep (execute "оплачено" / compensate "оплата возвращена");
 *       ShipStep (execute печатает "доставка не удалась" и БРОСАЕТ RuntimeException);
 *     - SagaOrchestrator (файл SagaOrchestrator.java): run(List<SagaStep>) —
 *       выполняет шаги, запоминая успешные; при исключении компенсирует успешные
 *       в обратном порядке.
 *   В main запустите сагу [Reserve, Charge, Ship] — Ship упадёт, должны
 *   откатиться Charge и Reserve.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   зарезервировано
 *   оплачено
 *   доставка не удалась
 *   оплата возвращена
 *   резерв снят
 *
 * ТРЕБОВАНИЯ:
 *   - компенсации выполняются ТОЛЬКО для успешно выполненных шагов;
 *   - порядок компенсаций — ОБРАТНЫЙ порядку выполнения;
 *   - оркестратор централизованно управляет ходом саги.
 *
 * ПОДСКАЗКА:
 *   Собирайте успешные шаги в список done; в catch идите по done с конца и
 *   вызывайте compensate(). Упавший шаг в done не попадает.
 */

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // TODO: запустите SagaOrchestrator.run(List.of(reserve, charge, ship))
    }
}
