package m26_reflection_annotations.practice;

/**
 * Задача 01 — Модуль 26: Объект Class
 *
 * ЗАДАНИЕ:
 *   Для нескольких объектов (String, Integer, своего класса)
 *   получите объект Class и выведите: полное имя, короткое имя,
 *   имя родительского класса.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Полное имя: java.lang.String, короткое: String, родитель: Object
 *   Полное имя: java.lang.Integer, короткое: Integer, родитель: Number
 *
 * ПОДСКАЗКА:
 *   "hi".getClass(), Integer.valueOf(5).getClass(), MyClass.class;
 *   getName(), getSimpleName(), getSuperclass().getSimpleName().
 */
public class Task01 {
    public static void main(String[] args) {
        // Получите Class разных объектов и выведите информацию
        Class<?> c1 = String.class;            // через литерал класса
        Class<?> c2 = "текст".getClass();      // через объект
        try {
            Class<?> c3 = Class.forName("java.lang.String");  // по имени (бросает исключение)
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Full name: " + c1.getName());         // "java.lang.String" — полное имя
        System.out.println("Short name"  + c1.getSimpleName());   // "String" — короткое имя
        System.out.println("Parent: " + c1.getSuperclass());   // класс-родитель
        System.out.println("Package: " + c1.getPackageName());  // пакет
    }
}
