/**
 * Задача 05 — Тема 10: Command — макрос (последовательность команд)
 *
 * ЗАДАНИЕ:
 *   Соберите составную команду (макрос) из нескольких простых:
 *     - интерфейс Command (файл Command.java): void execute();
 *     - получатель Robot (файл Robot.java): forward() печатает "вперёд",
 *       turnLeft() печатает "поворот налево";
 *     - ForwardCommand и TurnLeftCommand вызывают методы Robot;
 *     - MacroCommand (файл MacroCommand.java): хранит List<Command>, метод
 *       add(Command) и execute() запускает все команды по порядку.
 *   В main соберите макрос "вперёд, налево, вперёд" и выполните его одним execute().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   вперёд
 *   поворот налево
 *   вперёд
 *
 * ТРЕБОВАНИЯ:
 *   - MacroCommand сам реализует Command (макрос — тоже команда);
 *   - порядок выполнения = порядок добавления;
 *   - простые команды не знают о макросе.
 *
 * ПОДСКАЗКА:
 *   MacroCommand.execute() перебирает список и вызывает execute() каждой —
 *   это перекликается с Composite (макрос как «команда из команд»).
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: создайте Robot, соберите MacroCommand из Forward/TurnLeft/Forward, execute()
    }
}
