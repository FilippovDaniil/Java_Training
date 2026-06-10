/**
 * Задача 01 — Тема 14: MyHashMap с цепочками (структура + put/get)
 *
 * ЗАДАНИЕ:
 *   Реализуйте MyHashMap (файл MyHashMap.java) с разрешением коллизий
 *   цепочками: массив корзин, каждая корзина — связный список Entry.
 *   Реализуйте indexFor (ключ -> индекс корзины), put и get.
 *
 * ПРИМЕР / ВЫВОД:
 *   put(1,10); put(17,170)   // 1 и 17 могут попасть в одну корзину (коллизия) при capacity 16
 *   get(1)  -> 10
 *   get(17) -> 170
 *   get(99) -> null
 *   size = 2
 *
 * ТРЕБОВАНИЯ:
 *   - индекс корзины — неотрицательный: (hash & 0x7fffffff) % capacity;
 *   - put обновляет значение при существующем ключе (size не растёт);
 *   - коллизии (разные ключи в одной корзине) обрабатываются цепочкой;
 *   - НЕ использовать java.util.HashMap — пишем свою.
 *
 * ПОДСКАЗКА:
 *   Подберите ключи, дающие коллизию (например key и key+capacity), чтобы
 *   проверить работу цепочки.
 */

public class Task01 {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap(16);
        map.put(1, 10);
        map.put(17, 170);   // коллизия с 1 при capacity 16
        System.out.println("get(1)  = " + map.get(1));
        System.out.println("get(17) = " + map.get(17));
        System.out.println("get(99) = " + map.get(99));
        System.out.println("size = " + map.size());
    }
}
