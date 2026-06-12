package m09_strings.practice;

/**
 * Задача 03 — Модуль 09: Подсчёт гласных
 *
 * ЗАДАНИЕ:
 *   Посчитайте количество гласных букв в строке
 *   (для простоты — английские: a, e, i, o, u, регистр не важен).
 *
 * ПРИМЕР:
 *   "Hello World" → Гласных: 3
 *
 * ПОДСКАЗКА:
 *   Приведите строку к нижнему регистру, переберите символы,
 *   проверяйте принадлежность к гласным (можно через строку
 *   "aeiou".indexOf(c) >= 0).
 */
public class Task03 {
    public static void main(String[] args) {
        String s = "Hello World";
        s = s.toLowerCase();
        int count_of_glass = 0;
        String vowels = "aeiou";
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (vowels.indexOf(c) >= 0){
                count_of_glass = count_of_glass + 1;
            }
        }
        System.out.println("Count of glass: " + count_of_glass);
    }
}
