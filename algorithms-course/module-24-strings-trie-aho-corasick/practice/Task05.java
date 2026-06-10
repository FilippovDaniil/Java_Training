/**
 * Задача 05 — Тема 24: Поиск всех паттернов в тексте (Ахо-Корасик)
 *
 * ЗАДАНИЕ:
 *   Реализуйте полный Ахо-Корасик: бор + fail-ссылки + ПРОГОН текста, выдающий
 *   все вхождения всех паттернов (позиция + какой паттерн). Один проход по тексту.
 *
 * ПРИМЕР / ВЫВОД:
 *   паттерны: "he", "she", "hers"
 *   текст: "ushers"
 *   совпадения: "she" на позиции 1, "he" на позиции 2, "hers" на позиции 2
 *
 * ТРЕБОВАНИЯ:
 *   - построить автомат (бор + fail-ссылки BFS);
 *   - идти по тексту: на несовпадении переходить по fail-ссылке (как откат в КМП);
 *   - в каждом состоянии собирать все outputs (включая унаследованные по fail);
 *   - вернуть список совпадений (паттерн + начальная позиция в тексте).
 *
 * ПОДСКАЗКА:
 *   Позиция начала совпадения = индекс конца - длина паттерна + 1.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task05 {

    static class Node {
        Node[] ch = new Node[26];
        Node fail;
        List<Integer> out = new ArrayList<>();   // индексы паттернов
    }

    static Node root = new Node();
    static List<String> patterns = new ArrayList<>();

    static void addPattern(String p) {
        // TODO: построить бор, в конце out.add(индекс паттерна)
    }
    static void build() {
        // TODO: BFS-построение fail-ссылок + наследование out
    }

    /** Все совпадения: строки вида "паттерн@позиция". */
    static List<String> search(String text) {
        List<String> matches = new ArrayList<>();
        // TODO: прогон по тексту с переходами и сбором out
        return matches;
    }

    public static void main(String[] args) {
        for (String p : new String[]{"he", "she", "hers"}) { patterns.add(p); addPattern(p); }
        build();
        System.out.println(search("ushers"));
    }
}
