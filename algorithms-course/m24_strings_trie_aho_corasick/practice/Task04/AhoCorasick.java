package m24_strings_trie_aho_corasick.practice.task04;

/**
 * Автомат Ахо-Корасик: бор из паттернов + суффиксные (fail) ссылки.
 * В этой задаче фокус — ПОСТРОЕНИЕ структуры (insert паттернов и fail-ссылки BFS).
 * Поиск по тексту — в задаче 05.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AhoCorasick {

    static class Node {
        Node[] children = new Node[26];
        Node fail;                       // суффиксная ссылка
        List<Integer> outputs = new ArrayList<>();   // индексы паттернов, кончающихся здесь
    }

    final Node root = new Node();
    private int patternCount = 0;

    /** Добавить паттерн в бор. */
    public void addPattern(String pattern) {
        // TODO: спуститься/создать узлы; в конечном узле добавить outputs.add(индекс паттерна)
    }

    /**
     * Построить суффиксные ссылки обходом в ширину (BFS) по бору.
     * fail корня и его детей = root; для остальных — по ссылке родителя.
     */
    public void buildFailLinks() {
        // TODO: BFS: для каждого ребёнка c узла u по символу ch:
        //   найти fail, спускаясь по fail-ссылкам u; c.fail = соответствующий узел (или root);
        //   c.outputs += c.fail.outputs (наследуем выходы суффикса)
    }

    public int patternCount() { return patternCount; }
    void incPatternCount() { patternCount++; }   // вызывайте в addPattern при добавлении нового паттерна
}
