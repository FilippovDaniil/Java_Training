/**
 * Задача 05 — Тема 25: Базовый LZW (словарь из 256 символов)
 *
 * ЗАДАНИЕ:
 *   Реализуйте словарное сжатие LZW: словарь инициализируется одиночными
 *   символами (коды 0..255) и растёт на ходу. compress -> список кодов;
 *   decompress -> исходная строка. Проверьте round-trip.
 *
 * ПРИМЕР / ВЫВОД:
 *   "ABABABA" -> коды [65, 66, 256, 258, 65] (или подобные; короче исходного)
 *   decompress(коды) -> "ABABABA"
 *
 * ТРЕБОВАНИЯ:
 *   - compress: словарь String->код, старт 0..255; алгоритм из теории;
 *   - decompress: восстановить тот же словарь по кодам (зеркально);
 *   - round-trip: decompress(compress(s)) == s;
 *   - обработать классический «крайний случай» LZW при распаковке
 *     (код ещё не в словаре — строится из предыдущего + его первый символ).
 *
 * ПОДСКАЗКА:
 *   Сжатие — HashMap<String,Integer>; распаковка — HashMap<Integer,String>.
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {

    static List<Integer> compress(String s) {
        List<Integer> output = new ArrayList<>();
        // TODO: LZW-сжатие (словарь стартует одиночными символами)
        return output;
    }

    static String decompress(List<Integer> codes) {
        // TODO: LZW-распаковка (зеркальное построение словаря + крайний случай)
        return "";
    }

    public static void main(String[] args) {
        String s = "ABABABA";
        List<Integer> codes = compress(s);
        System.out.println("коды: " + codes);
        System.out.println("round-trip ok: " + s.equals(decompress(codes)));
    }
}
