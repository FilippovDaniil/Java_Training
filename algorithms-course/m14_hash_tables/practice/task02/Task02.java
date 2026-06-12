package m14_hash_tables.practice.task02;

/**
 * Задача 02 — Тема 14: put, get, remove
 *
 * ЗАДАНИЕ:
 *   Дополните MyHashMap методом remove(key) (put/get/containsKey уже даны).
 *   Корректно обрабатывайте удаление из головы, середины и конца цепочки,
 *   а также удаление несуществующего ключа.
 *
 * ПРИМЕР / ВЫВОД:
 *   put(1,10); put(17,170); put(33,330)   // все в одну корзину при capacity 16
 *   remove(17) -> true,  get(17) -> null
 *   remove(99) -> false (нет такого), size корректно уменьшается
 *
 * ТРЕБОВАНИЯ:
 *   - remove перевязывает цепочку через prev (или голову корзины);
 *   - remove несуществующего ключа -> false, size не меняется;
 *   - после remove get удалённого ключа -> null, остальные ключи доступны.
 *
 * ПОДСКАЗКА:
 *   Держите prev; если удаляемый — голова (prev==null), то buckets[i]=e.next.
 */

public class Task02 {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap(16);
        map.put(1, 10); map.put(17, 170); map.put(33, 330);   // коллизии в одной корзине
        System.out.println("remove(17) = " + map.remove(17));
        System.out.println("get(17)    = " + map.get(17));
        System.out.println("remove(99) = " + map.remove(99));
        System.out.println("size = " + map.size());
    }
}
