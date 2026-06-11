package m14_hash_tables.practice;

/**
 * Задача 06 — Тема 14: Открытая адресация (линейное пробирование)
 *
 * ЗАДАНИЕ:
 *   Реализуйте хеш-таблицу с ОТКРЫТОЙ АДРЕСАЦИЕЙ: все пары хранятся в самом
 *   массиве (без цепочек). При коллизии ищем следующую свободную ячейку
 *   линейным пробированием (index+1, index+2, ... по кругу).
 *
 * ПРИМЕР / ВЫВОД:
 *   capacity 8
 *   put(1,10); put(9,90)   // 1 и 9 претендуют на индекс 1 -> 9 уйдёт в следующий слот
 *   get(1) -> 10
 *   get(9) -> 90
 *
 * ТРЕБОВАНИЯ:
 *   - хранить ключи и значения в параллельных массивах + массив занятости;
 *   - put: при занятой ячейке пробовать (i+1)%cap, пока не найдём свободную/тот же ключ;
 *   - get: идти по той же последовательности проб, пока не найдём ключ или пустую ячейку;
 *   - для простоты можно не реализовывать remove (это требует «надгробий») — отметьте это.
 *
 * ПОДСКАЗКА:
 *   Не допускайте заполнения на 100% — оставляйте запас (load factor < 1),
 *   иначе поиск свободной ячейки зациклится.
 */

public class Task06 {

    static class OpenAddressingMap {
        private final int[] keys;
        private final int[] values;
        private final boolean[] used;
        private int size;

        public OpenAddressingMap(int capacity) {
            keys = new int[capacity]; values = new int[capacity]; used = new boolean[capacity];
        }

        private int hash(int key) { return (Integer.hashCode(key) & 0x7fffffff) % keys.length; }

        public void put(int key, int value) {
            // TODO: линейное пробирование до свободной ячейки или совпадения ключа
        }

        public Integer get(int key) {
            // TODO: идти по пробам, пока used[i]; если keys[i]==key -> value; пустая -> null
            return null;
        }

        public int size() { return size; }
    }

    public static void main(String[] args) {
        OpenAddressingMap map = new OpenAddressingMap(8);
        map.put(1, 10);
        map.put(9, 90);   // коллизия с 1 -> следующий слот
        System.out.println("get(1) = " + map.get(1));
        System.out.println("get(9) = " + map.get(9));
    }
}
