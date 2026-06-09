/**
 * Задача 03 — Тема 03: ISP — разрезать «толстый» интерфейс
 *
 * ЗАДАНИЕ:
 *   Толстый интерфейс Worker { work(); eat(); } заставляет робота
 *   реализовывать eat() заглушкой — это запах. Разделите по ролям:
 *     - Workable (файл Workable.java): void work();
 *     - Feedable (файл Feedable.java): void eat();
 *     - Human реализует обе роли; Robot — только Workable.
 *   В main: всех заставьте работать; покормите только тех, кто Feedable.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Человек работает
 *   Робот работает
 *   Человек ест
 *
 * ТРЕБОВАНИЯ:
 *   - нет ни одного метода-заглушки с UnsupportedOperationException;
 *   - Robot не зависит от метода eat(), который ему не нужен;
 *   - «покормить» доступно через тип Feedable.
 *
 * ПОДСКАЗКА:
 *   ISP — это SRP для интерфейсов: один интерфейс = одна роль. Клиент
 *   (вызывающий код) зависит только от той роли, которая ему нужна.
 */

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // TODO: List<Workable> с Human и Robot — все работают;
        //       покормите только тех, кто instanceof Feedable
    }
}
