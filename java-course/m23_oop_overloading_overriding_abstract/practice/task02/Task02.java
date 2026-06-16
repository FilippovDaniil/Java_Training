package m23_oop_overloading_overriding_abstract.practice.task02;

/**
 * Задача 02 — Модуль 23: Переопределение и super
 *
 * ЗАДАНИЕ:
 *   Класс Employee с методом describe(), печатающим "Сотрудник: <name>".
 *   Класс Manager extends Employee переопределяет describe():
 *   сначала вызывает версию родителя через super.describe(),
 *   затем добавляет "Должность: менеджер".
 *   В main создайте обоих и вызовите describe().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Сотрудник: Иван
 *   Сотрудник: Пётр
 *   Должность: менеджер
 *
 * ПОДСКАЗКА:
 *   @Override + super.describe() в начале переопределённого метода.
 */

// TODO: класс Manager extends Employee, переопределяющий describe() с super
public class Task02 {
    public static void main(String[] args) {
        // Создайте Employee и Manager, вызовите describe()
        Employee[] employees = {
                new Manager("Petr"),
                new Employee("Ivan")
        };
        for (Employee employee : employees) {
            employee.describe();
        }
    }
}
