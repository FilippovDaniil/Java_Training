package m22_oop_encapsulation_polymorphism_interfaces.practice.task02;

/**
 * Задача 02 — Модуль 22: Несколько интерфейсов
 *
 * ЗАДАНИЕ:
 *   Объявлены интерфейсы Flyable (fly()) и Swimmable (swim()).
 *   1. Класс Duck реализует ОБА интерфейса.
 *   2. Класс Fish реализует только Swimmable.
 *   3. Класс Plane реализует только Flyable.
 *   В main создайте объекты и вызовите доступные им методы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Утка летит
 *   Утка плывёт
 *   Рыба плывёт
 *   Самолёт летит
 *
 * ПОДСКАЗКА:
 *   class Duck implements Flyable, Swimmable { ... }
 */

// TODO: классы Duck (Flyable+Swimmable), Fish (Swimmable), Plane (Flyable)
public class Task02 {
    public static void main(String[] args) {
        // Создайте Duck, Fish, Plane и вызовите их методы
        Flyable duck = new Duck("Duck");
        Swimmable duck2 = new Duck("Duck2");
        Swimmable fish = new Fish("Fish");
        Flyable plane = new Plane("Plane");
        Duck duck3 = new Duck("Duck3");

        duck.fly();
        duck2.swim();
        fish.swim();
        plane.fly();
        duck3.fly();
        duck3.swim();
    }
}
