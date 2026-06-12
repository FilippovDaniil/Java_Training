package m10_strategy_command.practice.task03;

/**
 * Задача 03 — Тема 10: Command (запрос как объект)
 *
 * ЗАДАНИЕ:
 *   Инкапсулируйте действия над лампой в объекты-команды:
 *     - интерфейс Command (файл Command.java): void execute();
 *     - получатель Light (файл Light.java): on() печатает "свет включён",
 *       off() — "свет выключен";
 *     - LightOnCommand и LightOffCommand хранят Light и в execute() вызывают
 *       соответствующий метод;
 *     - Invoker (файл Invoker.java): setCommand(Command) и pressButton()
 *       вызывает execute() у текущей команды (не зная её деталей).
 *   В main: создайте лампу, команды, передайте их в Invoker и «нажмите кнопку».
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   свет включён
 *   свет выключен
 *
 * ТРЕБОВАНИЯ:
 *   - Invoker знает только интерфейс Command, не получателя Light;
 *   - каждая команда инкапсулирует «что и над кем» сделать;
 *   - получатель (Light) не знает о командах.
 *
 * ПОДСКАЗКА:
 *   Command отделяет «того, кто просит» (Invoker) от «того, кто делает» (Light).
 *   Простейшая команда в Java — Runnable.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: Light light; Invoker; setCommand(new LightOnCommand(light)); pressButton(); и off
    }
}
