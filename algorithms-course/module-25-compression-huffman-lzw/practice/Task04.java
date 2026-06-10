/**
 * Задача 04 — Тема 25: Сохранить таблицу кодов в поток (формат)
 *
 * ЗАДАНИЕ:
 *   Придумайте и реализуйте простой текстовый ФОРМАТ для сохранения таблицы
 *   кодов Хаффмана, чтобы по нему можно было восстановить декодер. Сериализуйте
 *   таблицу в строку и десериализуйте обратно, проверив совпадение.
 *
 * ПРИМЕР / ВЫВОД (формат — на ваш выбор, например "символ:код" через ';'):
 *   коды {a=0, b=10, c=11}
 *   сериализовано: "a:0;b:10;c:11"
 *   десериализовано обратно в {a=0, b=10, c=11} (совпало)
 *
 * ТРЕБОВАНИЯ:
 *   - serialize(Map) -> String; deserialize(String) -> Map;
 *   - формат должен переживать round-trip (deserialize(serialize(x)) == x);
 *   - продумайте экранирование, если символом может быть разделитель
 *     (для простоты можно ограничить алфавит — отметьте это).
 *
 * ПОДСКАЗКА:
 *   Альтернатива хранению кодов — хранить ЧАСТОТЫ: по ним строится то же дерево.
 *   Но здесь тренируем именно сериализацию таблицы.
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class Task04 {

    static String serialize(Map<Character, String> codes) {
        // TODO: собрать строку "символ:код" через разделитель
        return "";
    }

    static Map<Character, String> deserialize(String data) {
        Map<Character, String> codes = new LinkedHashMap<>();
        // TODO: разобрать строку обратно в карту
        return codes;
    }

    public static void main(String[] args) {
        Map<Character, String> codes = new LinkedHashMap<>();
        codes.put('a', "0"); codes.put('b', "10"); codes.put('c', "11");
        String data = serialize(codes);
        System.out.println("сериализовано: " + data);
        System.out.println("round-trip ok: " + codes.equals(deserialize(data)));
    }
}
