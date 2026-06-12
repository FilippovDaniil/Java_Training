package m09_strings.practice;

/**
 * Задача 04 — Модуль 09: Палиндром
 *
 * ЗАДАНИЕ:
 *   Определите, является ли введённая строка палиндромом
 *   (читается одинаково слева направо и справа налево).
 *   Игнорируйте регистр и пробелы.
 *
 * ПРИМЕРЫ:
 *   "ABBA"        → Палиндром
 *   "А роза упала на лапу Азора" → Палиндром
 *   "Hello"       → Не палиндром
 *
 * ПОДСКАЗКА:
 *   Уберите пробелы (replace(" ", "")), приведите к одному регистру,
 *   сравните строку с её разворотом через equals.
 */
import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        // Ваш код здесь
        s = s.replace(" ","");
        int length = s.length();
        boolean result;
        String first_polovina = "";
        String second_polovina = "";
        if (length % 2 == 0){
            first_polovina = s.substring(0,length/2);
            second_polovina = s.substring(length/2);
        }else{
            first_polovina = s.substring(0,length/2);
            second_polovina = s.substring(length/2+1);
        }

        StringBuilder second = new StringBuilder(second_polovina);
        second.reverse();
        second_polovina = second.toString();

        if (first_polovina.equals(second_polovina)){
            result = true;
        }else{
            result = false;
        }

        if (result == true){
            System.out.println("Palindrom");
        } else{
            System.out.println("Ne Palindrom");
        }

        scanner.close();
    }
}
