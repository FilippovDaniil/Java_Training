package m24_strings_trie_aho_corasick.practice.task04;

/**
 * Задача 04 — Тема 24: Ахо-Корасик — построение бора и суффиксных ссылок
 *
 * ЗАДАНИЕ:
 *   Реализуйте AhoCorasick (файл AhoCorasick.java): добавление паттернов в бор
 *   (addPattern) и построение суффиксных (fail) ссылок обходом в ширину
 *   (buildFailLinks). Поиск по тексту будет в задаче 05 — здесь только структура.
 *
 * ПРИМЕР / ВЫВОД:
 *   паттерны: "he", "she", "his", "hers"
 *   после buildFailLinks: автомат построен, fail-ссылки проставлены
 *   (проверка: fail у узла "she"->"e" должна вести в узел "he"->"e")
 *
 * ТРЕБОВАНИЯ:
 *   - addPattern строит бор (как Trie), помечает конец паттерна (outputs);
 *   - buildFailLinks через BFS: fail корня/детей = root; для остальных —
 *     спуск по fail-ссылкам родителя;
 *   - наследовать outputs по fail-ссылке (для вложенных совпадений).
 *
 * ПОДСКАЗКА:
 *   Суффиксная ссылка — аналог префикс-функции КМП (тема 11), но для бора.
 *   Стройте строго BFS (по уровням), чтобы fail родителя был уже готов.
 */

public class Task04 {
    public static void main(String[] args) {
        AhoCorasick ac = new AhoCorasick();
        for (String p : new String[]{"he", "she", "his", "hers"}) ac.addPattern(p);
        ac.buildFailLinks();
        System.out.println("паттернов добавлено: " + ac.patternCount());
        System.out.println("автомат построен (fail-ссылки проставлены)");
    }
}
