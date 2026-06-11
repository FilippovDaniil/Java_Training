package m04_singleton_factory_method.practice.task01;

/**
 * Задача 01 — Тема 04: Singleton (eager)
 *
 * ЗАДАНИЕ:
 *   Реализуйте IdSequence (файл IdSequence.java) как Singleton, выдающий
 *   возрастающие идентификаторы на всю программу:
 *     - приватный конструктор;
 *     - статическое поле INSTANCE (создаётся сразу);
 *     - метод getInstance();
 *     - синхронизированный метод long next() — возвращает 1, 2, 3, ...
 *   В main получите экземпляр в двух местах, убедитесь, что это ОДИН объект (==),
 *   и что счётчик общий (продолжается, а не сбрасывается).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   id1 = 1
 *   id2 = 2
 *   Один и тот же объект: true
 *   id3 = 3
 *
 * ТРЕБОВАНИЯ:
 *   - конструктор приватный (никто не создаст второй экземпляр);
 *   - INSTANCE — static final, инициализируется при загрузке класса;
 *   - счётчик единый для всех обращений.
 *
 * ПОДСКАЗКА:
 *   private IdSequence() {}
 *   private static final IdSequence INSTANCE = new IdSequence();
 *   public static IdSequence getInstance() { return INSTANCE; }
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: получите IdSequence дважды, сравните через ==, вызовите next()
    }
}
