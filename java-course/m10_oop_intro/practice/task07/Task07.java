package m10_oop_intro.practice.task07;

/**
 * Задача 07 — Модуль 10 (МИНИ-ПРОЕКТ): Зоопарк
 *
 * ЗАДАНИЕ:
 *   Смоделируйте небольшой зоопарк через иерархию классов.
 *   1. Базовый класс Animal: поля name, age; метод describe(),
 *      выводящий "<name>, возраст <age>"; метод makeSound(),
 *      выводящий "<name> издаёт звук".
 *   2. Потомки: Lion, Elephant, Monkey (extends Animal).
 *      У каждого добавьте собственное поведение, например:
 *        - Lion: метод hunt() → "<name> охотится";
 *        - Elephant: поле trunkLength + метод spray() → "<name> поливает водой";
 *        - Monkey: метод climb() → "<name> залезает на дерево".
 *   3. В main создайте массив Animal[] из нескольких разных животных,
 *      в цикле вызовите для каждого describe() и makeSound().
 *   4. Дополнительно выведите общее количество животных и средний возраст.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   === ЗООПАРК ===
 *   Симба, возраст 5
 *   Симба издаёт звук
 *   Дамбо, возраст 10
 *   Дамбо издаёт звук
 *   ...
 *   Всего животных: 3
 *   Средний возраст: 6.0
 *
 * ПОДСКАЗКА:
 *   Объект потомка можно хранить в переменной/массиве типа Animal.
 *   Поля name и age наследуются всеми потомками.
 */

public class Task07 {
    public static void main(String[] args) {
        // Объект потомка хранится в переменной типа Animal
        Animal[] zoo = {
                new Lion("Симба", 5),
                new Elephant("Дамбо", 10, 150),
                new Monkey("Чита", 3)
        };

        System.out.println("=== ЗООПАРК ===");

        int sumAge = 0;
        for (Animal animal : zoo) {
            animal.describe();
            animal.makeSound();
            sumAge += animal.age;
        }

        double averageAge = (double) sumAge / zoo.length;
        System.out.println("Всего животных: " + zoo.length);
        System.out.println("Средний возраст: " + averageAge);
    }
}
