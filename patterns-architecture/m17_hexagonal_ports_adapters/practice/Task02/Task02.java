package m17_hexagonal_ports_adapters.practice.task02;

/**
 * Задача 02 — Тема 17: входной порт (driving) + адаптер
 *
 * ЗАДАНИЕ:
 *   Ядро ПРЕДОСТАВЛЯЕТ наружу сценарий через входной порт, а driving-адаптер
 *   его вызывает:
 *     - CalculatorUseCase (файл CalculatorUseCase.java) — входной порт: long add(long a, long b);
 *     - CalculatorService (файл CalculatorService.java) — ЯДРО: реализует порт (a + b);
 *     - ConsoleCalculatorAdapter (файл ...) — driving-адаптер: хранит
 *       CalculatorUseCase, метод run(a, b) печатает "результат: <сумма>".
 *   В main подключите ядро к адаптеру и вызовите run(2, 3).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   результат: 5
 *
 * ТРЕБОВАНИЯ:
 *   - входной порт CalculatorUseCase РЕАЛИЗУЕТ ядро (CalculatorService);
 *   - driving-адаптер вызывает ядро ТОЛЬКО через порт (не через конкретный класс);
 *   - адаптер не содержит вычислений — он лишь принимает ввод и зовёт порт.
 *
 * ПОДСКАЗКА:
 *   Driving (входной) порт — это API сценария. В проде driving-адаптером был бы
 *   REST-контроллер; здесь — «консоль». Оба лишь вызывают порт ядра.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: ConsoleCalculatorAdapter с CalculatorService; run(2, 3)
    }
}
