/**
 * Задача 06 — Тема 11: Поиск подстроки в циклической строке
 *
 * ЗАДАНИЕ:
 *   Дана циклическая строка (конец соединён с началом). Проверьте, встречается
 *   ли паттерн в ней с учётом «заворота» через границу.
 *   Классический приём: искать паттерн в удвоенной строке T+T, но не дальше
 *   позиции (n-1), чтобы не засчитать вхождения за пределами одного оборота.
 *
 * ПРИМЕР / ВЫВОД:
 *   T = "ABCD" (циклическая), P = "CDAB" -> найдено (заворот: CD + AB)
 *   T = "ABCD", P = "DA" -> найдено (D в конце + A в начале)
 *   T = "ABCD", P = "DC" -> не найдено
 *
 * ТРЕБОВАНИЯ:
 *   - длина паттерна не больше длины строки (иначе сразу false или особый случай);
 *   - использовать поиск в T+T, ограничив стартовую позицию диапазоном [0, n-1];
 *   - можно опираться на любой поиск из задач 01/03/04.
 *
 * ПОДСКАЗКА:
 *   doubled = T + T; ищем P в doubled; вхождение считается, если его старт < n.
 */

public class Task06 {

    static boolean containsCyclic(String text, String pattern) {
        // TODO: поиск в text+text со стартом < text.length()
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsCyclic("ABCD", "CDAB"));   // true
        System.out.println(containsCyclic("ABCD", "DA"));      // true
        System.out.println(containsCyclic("ABCD", "DC"));      // false
    }
}
