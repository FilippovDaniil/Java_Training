package m10_oop_intro.practice.task02;

/**
 * Задача 02 — Модуль 10: Метод объекта (поведение)
 *
 * ЗАДАНИЕ:
 *   Дополните класс Car методом start(), который выводит
 *   "<brand> заводится...". В main создайте две машины разных марок
 *   и вызовите их методы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Toyota заводится...
 *   BMW заводится...
 *
 * ПОДСКАЗКА:
 *   Внутри метода обращайтесь к полю brand напрямую (или через this.brand).
 */

public class Task02 {
    public static void main(String[] args) {
        // Создайте объекты Car и вызовите start()
        Car car1 = new Car("BMW");
        Car car2 = new Car();
        car2.setBrand("Mercedes");
        car1.start();
        car2.start();
    }
}
