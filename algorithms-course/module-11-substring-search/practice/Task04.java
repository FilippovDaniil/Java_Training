/**
 * Задача 04 — Тема 11: Бойер-Мур (эвристика стоп-символа)
 *
 * ЗАДАНИЕ:
 *   Реализуйте поиск подстроки методом Бойера-Мура только с эвристикой
 *   СТОП-СИМВОЛА (bad character). Сравнение паттерна идёт справа налево; при
 *   несовпадении паттерн сдвигается так, чтобы несовпавший символ текста встал
 *   напротив его последнего вхождения в паттерне (или за паттерн, если его там нет).
 *
 * ПРИМЕР / ВЫВОД:
 *   T = "HERE IS A SIMPLE EXAMPLE", P = "EXAMPLE"
 *   вхождения: [17]
 *   T = "ABAAABCD", P = "ABC" -> [3]
 *
 * ТРЕБОВАНИЯ:
 *   - таблица последнего вхождения каждого символа в паттерне (last[char]);
 *   - сравнение справа налево; сдвиг по стоп-символу;
 *   - сдвиг всегда >= 1 (иначе зацикливание): shift = max(1, j - last[c]).
 *
 * ПОДСКАЗКА:
 *   last[c] = индекс последнего вхождения символа c в паттерне (или -1).
 *   При несовпадении на позиции j: сдвинуть i на max(1, j - last[T[i+j]]).
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static List<Integer> boyerMoore(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        // TODO: построить last[256]; сравнивать справа налево; сдвигать по стоп-символу
        return result;
    }

    public static void main(String[] args) {
        System.out.println(boyerMoore("HERE IS A SIMPLE EXAMPLE", "EXAMPLE"));   // [17]
        System.out.println(boyerMoore("ABAAABCD", "ABC"));                        // [3]
    }
}
