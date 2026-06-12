package m17_hexagonal_ports_adapters.practice.task03;

/**
 * Задача 03 — Тема 17: полный гексагон (вход + ядро + выход)
 *
 * ЗАДАНИЕ:
 *   Соберите всю шестиугольную схему: driving-адаптер → входной порт → ядро →
 *   выходной порт → driven-адаптер.
 *     - выходной порт GreetingRepository (файл ...): String find(String lang)
 *       (возвращает приветственное слово по языку); InMemoryGreetingRepository
 *       ("ru" → "Привет", "en" → "Hello");
 *     - входной порт GreetUseCase (файл ...): String greet(String lang, String name);
 *     - ЯДРО GreetService (файл GreetService.java): реализует GreetUseCase, зависит
 *       от GreetingRepository; greet = find(lang) + ", " + name;
 *     - driving-адаптер ConsoleGreetAdapter (файл ...): зовёт useCase, печатает результат.
 *   В main соберите цепочку и поздоровайтесь по-русски.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Привет, Аня
 *
 * ТРЕБОВАНИЯ:
 *   - ядро зависит от ОБОИХ портов (реализует входной, использует выходной);
 *   - ядро не знает о конкретных адаптерах (только порты);
 *   - сборка (composition root) — в main: подключает адаптеры к портам (DI).
 *
 * ПОДСКАЗКА:
 *   new ConsoleGreetAdapter(new GreetService(new InMemoryGreetingRepository())).
 *   Зависимости направлены внутрь, к ядру; снаружи — только адаптеры.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: соберите гексагон и вызовите run("ru", "Аня")
    }
}
