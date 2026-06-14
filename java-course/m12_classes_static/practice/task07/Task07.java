package m12_classes_static.practice.task07;

import org.h2.util.json.JSONItemType;

/**
 * Задача 07 — Модуль 12 (МИНИ-ПРОЕКТ): Генератор сотрудников с ID
 *
 * ЗАДАНИЕ:
 *   Реализуйте систему учёта сотрудников, где ID выдаётся
 *   автоматически с помощью статического счётчика.
 *   Класс Employee:
 *     - private поля: id (int), name (String), salary (double);
 *     - static поле nextId (следующий свободный ID, начиная с 1);
 *     - конструктор (name, salary): id присваивается из nextId,
 *       после чего nextId увеличивается;
 *     - геттеры; метод toString();
 *     - static-метод getTotalEmployees(), возвращающий, сколько
 *       сотрудников создано.
 *   В main создайте 3-4 сотрудников, выведите каждого (с авто-ID)
 *   и общее количество.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Employee{id=1, name='Иван', salary=50000.0}
 *   Employee{id=2, name='Мария', salary=70000.0}
 *   Employee{id=3, name='Пётр', salary=60000.0}
 *   Всего сотрудников: 3
 *
 * ПОДСКАЗКА:
 *   Идея авто-ID: this.id = nextId; nextId++;
 *   Счётчик общий для класса (static), ID — индивидуальный (instance).
 */

public class Task07 {
    public static void main(String[] args) {
        // Создайте сотрудников и выведите их + общее количество
        Employee employee1 = new Employee("Danya",177777.55);
        Employee employee2 = new Employee("Vanya",1777.55);
        Employee employee3 = new Employee("Sanya",17.55);
        Employee employee11 = new Employee("Danya",177777.55);
        Employee employee21 = new Employee("Vanya",1777.55);
        Employee employee31 = new Employee("Sanya",17.55);

        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);
        System.out.println(employee11);
        System.out.println(employee21);
        System.out.println(employee31);

        System.out.println("Kolichestvo: " + Employee.nextId);
    }
}
