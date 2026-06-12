package m16_dependency_injection_ioc.practice.task01;

/**
 * Задача 01 — Тема 16: Constructor Injection (ручной DI)
 *
 * ЗАДАНИЕ:
 *   Внедрите зависимость через конструктор (а не создавайте её внутри):
 *     - интерфейс MessageService (файл MessageService.java): String message(String name);
 *     - PoliteMessageService (файл PoliteMessageService.java): возвращает
 *       "Здравствуйте, " + name;
 *     - Greeter (файл Greeter.java): получает MessageService В КОНСТРУКТОРЕ
 *       (поле final), метод greet(name) делегирует сервису.
 *   В main создайте PoliteMessageService, внедрите его в Greeter, поздоровайтесь.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Здравствуйте, Аня
 *
 * ТРЕБОВАНИЯ:
 *   - Greeter НЕ создаёт MessageService внутри (нет new PoliteMessageService());
 *   - зависимость приходит через конструктор и хранится в final-поле;
 *   - Greeter знает только интерфейс MessageService.
 *
 * ПОДСКАЗКА:
 *   Constructor injection = объект всегда получает обязательную зависимость при
 *   создании и не может оказаться «полусобранным».
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте Greeter с PoliteMessageService, вызовите greet("Аня")
    }
}
