package m01_oop_principles.practice.task02;

/**
 * Задача 02 — Тема 01: Наследование (is-a)
 *
 * ЗАДАНИЕ:
 *   Постройте иерархию сотрудников:
 *     - Employee (файл Employee.java): поле name, метод baseSalary() = 50000,
 *       метод monthlySalary() (по умолчанию = baseSalary());
 *     - Manager (файл Manager.java) extends Employee: переопределяет
 *       monthlySalary() = super.baseSalary() + бонус 30000.
 *   В main создайте обоих и выведите их зарплаты.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Иванов (Employee): 50000
 *   Петров (Manager): 80000
 *
 * ТРЕБОВАНИЯ:
 *   - Manager наследует Employee (отношение «менеджер ЕСТЬ сотрудник»);
 *   - бонус добавляется через super.baseSalary(), без копипасты базовой логики;
 *   - @Override на переопределённом методе.
 *
 * ПОДСКАЗКА:
 *   Проверьте себя фразой «Manager is a Employee» — она должна звучать логично.
 *   Наследование здесь оправдано, потому что менеджер действительно частный
 *   случай сотрудника.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: создайте Employee и Manager, выведите monthlySalary() каждого
    }
}
