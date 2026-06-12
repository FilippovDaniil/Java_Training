package m10_oop_intro.practice.task03;

import java.util.Arrays;

/**
 * Задача 03 — Модуль 10: null и NullPointerException
 *
 * ЗАДАНИЕ:
 *   1. Объявите переменную типа Student со значением null.
 *   2. Перед обращением к её полю/методу проверьте, что она не null.
 *      Если null — выведите "Студент не задан".
 *   3. Затем создайте реальный объект, заполните имя и выведите его
 *      через метод printInfo().
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Студент не задан
 *   Студент: Иван
 *
 * ПОДСКАЗКА:
 *   Обращение к методу через null приводит к NullPointerException.
 *   Защита: if (student != null) { ... }
 */

public class Task03 {
    public static void main(String[] args) {
        Student student = null;
        // Ваш код здесь
        Student student1 = new Student("Ivan");

        Student[] Array_Of_Student = new Student[2];
        Array_Of_Student[0] = student;
        Array_Of_Student[1] = student1;

        for (Student student_in_arr : Array_Of_Student) {
            if(student_in_arr == null){
                System.out.println("Student ne zadan");
            }else{
                System.out.println(student_in_arr);
            }
        }


    }
}
