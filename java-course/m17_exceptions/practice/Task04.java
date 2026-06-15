package m17_exceptions.practice;

/**
 * Задача 04 — Модуль 17: throw (выброс исключения)
 *
 * ЗАДАНИЕ:
 *   Напишите метод checkAge(int age), который БРОСАЕТ
 *   IllegalArgumentException с понятным сообщением, если возраст
 *   меньше 0 или больше 150. Иначе выводит "Возраст корректен: <age>".
 *   В main вызовите метод для нескольких значений внутри try-catch.
 *
 * ПРИМЕРЫ:
 *   checkAge(25)  → Возраст корректен: 25
 *   checkAge(-3)  → перехват: "Недопустимый возраст: -3"
 *   checkAge(200) → перехват: "Недопустимый возраст: 200"
 *
 * ПОДСКАЗКА:
 *   throw new IllegalArgumentException("Недопустимый возраст: " + age);
 */
public class Task04 {
    public static void main(String[] args) {
        // Вызовите checkAge для разных значений в try-catch
        checkAge(24);
        checkAge(-5);
        checkAge(120);

    }

    // TODO: метод checkAge(int age) с throw
    private static void checkAge(int age){
        if(age < 0 || age > 120){
            throw new IllegalArgumentException("No valid age: " + age);
        }else{
            System.out.println("Age is valid: " + age);
        }
    }
}
