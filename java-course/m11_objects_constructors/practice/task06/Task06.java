package m11_objects_constructors.practice.task06;

/**
 * Задача 06 — Модуль 11: Полноценный класс
 *
 * ЗАДАНИЕ:
 *   Соберите «правильный» класс Student с нуля:
 *     - private поля: name (String), group (String), gpa (double);
 *     - конструктор со всеми полями;
 *     - геттеры для всех полей;
 *     - сеттер для gpa с валидацией (0.0..5.0);
 *     - метод toString() для читаемого вывода.
 *   В main создайте 2-3 студентов, выведите их и попробуйте задать
 *   некорректный gpa.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Student{name='Иван', group='ИС-31', gpa=4.5}
 *   Недопустимый GPA: 6.0
 *
 * ПОДСКАЗКА:
 *   Это закрепление всего модуля: конструктор + инкапсуляция +
 *   валидация + toString.
 */

public class Task06 {
    public static void main(String[] args) {
        // Создайте студентов и продемонстрируйте работу класса
        Student vanya = new Student();
        Student danya = new Student();
        Student lesha = new Student("Lesha","BIB202",4.99);

        vanya.setGpa(4.55);
        vanya.setGroup("BOB202");
        vanya.setName("Vanya");

        danya.setGpa(7.77);
        danya.setName("danya");
        danya.setGroup("BIB202222");

        System.out.println(danya);
        System.out.println(vanya);
        System.out.println(lesha);
    }
}
