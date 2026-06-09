/**
 * Задача 04 — Тема 22: лечим Feature Envy
 *
 * ЗАДАНИЕ:
 *   Раньше метод customerLabel(Customer c) жил в классе Order и собирал строку из
 *   ПОЛЕЙ Customer (c.getFirstName()+" "+c.getLastName()) — это «зависть к данным».
 *   Переместите поведение туда, где данные:
 *     - Customer (файл Customer.java): firstName, lastName; метод label() →
 *       "firstName lastName".
 *   В main создайте Customer и получите его label() (метод теперь на своём месте).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Анна Иванова
 *
 * ТРЕБОВАНИЯ:
 *   - метод label() живёт в Customer (он использует ЕГО данные);
 *   - никакой внешний класс не собирает строку из полей Customer;
 *   - поведение переехало к данным (Information Expert).
 *
 * ПОДСКАЗКА:
 *   Feature Envy = метод больше работает с чужими данными, чем со своими.
 *   Лечение — переместить метод в класс-владелец данных.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Customer("Анна", "Иванова"), выведите label()
    }
}
