/**
 * Задача 05 — Тема 21: откат саги по результату шага (без исключений)
 *
 * ЗАДАНИЕ:
 *   Альтернативный стиль: шаг возвращает признак успеха (boolean), а сага при
 *   неудаче компенсирует успешные шаги В ОБРАТНОМ порядке.
 *     - интерфейс SagaStep (файл SagaStep.java): boolean execute(); void compensate();
 *     - Step1 (execute → печатает "шаг 1 ok", true; compensate "откат 1");
 *       Step2 (аналогично "шаг 2 ok" / "откат 2");
 *       Step3 (execute печатает "шаг 3 FAIL" и возвращает false; compensate "откат 3");
 *     - Saga (файл Saga.java): run(List<SagaStep>) — выполняет шаги; на первом
 *       false компенсирует успешные в обратном порядке и останавливается.
 *   В main запустите сагу [Step1, Step2, Step3].
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   шаг 1 ok
 *   шаг 2 ok
 *   шаг 3 FAIL
 *   откат 2
 *   откат 1
 *
 * ТРЕБОВАНИЯ:
 *   - решение об откате принимается по возвращаемому значению, а не по исключению;
 *   - компенсируются только успешные шаги, в обратном порядке;
 *   - упавший шаг (Step3) не компенсируется (его execute не удался).
 *
 * ПОДСКАЗКА:
 *   if (!step.execute()) { откат done с конца; return; } else done.add(step);
 *   Это тот же принцип, что в Task03, но через boolean вместо исключения.
 */

import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        // TODO: Saga.run(List.of(new Step1(), new Step2(), new Step3()))
    }
}
