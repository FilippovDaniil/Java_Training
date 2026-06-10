/**
 * Задача 02 — Тема 25: Дерево Хаффмана и коды символов
 *
 * ЗАДАНИЕ:
 *   По частотам символов постройте дерево Хаффмана (жадно, через min-heap) и
 *   получите код каждого символа (путь от корня: 0 — влево, 1 — вправо).
 *
 * ПРИМЕР / ВЫВОД (коды могут отличаться, но частым — короче):
 *   "aaabbc" (a:3,b:2,c:1)
 *   коды: a=0, b=10, c=11   (или симметричный вариант)
 *   ВАЖНО: код частого 'a' короче кодов редких 'b','c'.
 *
 * ТРЕБОВАНИЯ:
 *   - листья — символы с частотами; PriorityQueue по частоте;
 *   - объединять два минимальных узла в новый (freq = сумма), пока не останется один;
 *   - коды получить обходом дерева (DFS), накапливая биты;
 *   - спец-случай одного уникального символа: код "0".
 *
 * ПОДСКАЗКА:
 *   Узел: freq + (символ для листа) + left/right. Внутренние узлы символа не имеют.
 */

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Task02 {

    static class Node {
        int freq; Character ch; Node left, right;
        Node(int freq, Character ch) { this.freq = freq; this.ch = ch; }
        Node(int freq, Node l, Node r) { this.freq = freq; this.left = l; this.right = r; }
        boolean isLeaf() { return ch != null; }
    }

    static Map<Character, String> buildCodes(Map<Character, Integer> freq) {
        Map<Character, String> codes = new TreeMap<>();
        // TODO: PriorityQueue<Node> по freq; объединять минимальные; DFS для кодов
        //       обработать спец-случай одного символа
        return codes;
    }

    public static void main(String[] args) {
        Map<Character, Integer> freq = new TreeMap<>();
        freq.put('a', 3); freq.put('b', 2); freq.put('c', 1);
        System.out.println(buildCodes(freq));
    }
}
