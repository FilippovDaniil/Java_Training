package m25_compression_huffman_lzw.practice;

/**
 * Задача 03 — Тема 25: Сжатие и распаковка строки (Хаффман)
 *
 * ЗАДАНИЕ:
 *   Реализуйте полный цикл Хаффмана: encode(s) -> битовая строка (из '0'/'1')
 *   + дерево/коды; decode(биты, дерево) -> исходная строка. Проверьте, что
 *   decode(encode(s)) == s.
 *
 * ПРИМЕР / ВЫВОД:
 *   s = "abracadabra"
 *   закодировано в N бит (N меньше, чем 8*длина для ASCII)
 *   decode -> "abracadabra"   (round-trip совпал)
 *
 * ТРЕБОВАНИЯ:
 *   - encode: построить дерево, заменить символы кодами (вернуть строку битов);
 *   - decode: идти по дереву битам потока, на листе — выдать символ, вернуться в корень;
 *   - round-trip: decode(encode(s)) == s;
 *   - спец-случай одного уникального символа.
 *
 * ПОДСКАЗКА:
 *   Удобно вернуть из encode и биты, и корень дерева (или коды) — они нужны для decode.
 */

import java.util.Map;

public class Task03 {

    static class Node {
        int freq; Character ch; Node left, right;
        Node(int f, Character c){freq=f;ch=c;} Node(int f,Node l,Node r){freq=f;left=l;right=r;}
        boolean isLeaf(){ return ch != null; }
    }

    static Node tree;   // дерево, построенное при encode (для decode)

    static String encode(String s) {
        // TODO: частоты -> дерево (сохранить в tree) -> коды -> битовая строка
        return "";
    }

    static String decode(String bits) {
        // TODO: идти по tree битам; на листе выдавать символ; вернуть строку
        return "";
    }

    public static void main(String[] args) {
        String s = "abracadabra";
        String bits = encode(s);
        String back = decode(bits);
        System.out.println("бит: " + bits.length() + " (вместо " + s.length() * 8 + ")");
        System.out.println("round-trip ok: " + s.equals(back));
    }
}
