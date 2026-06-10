/**
 * Задача 03 — Тема 14: Автоматическое расширение (rehash)
 *
 * ЗАДАНИЕ:
 *   Добавьте в хеш-таблицу с цепочками авторасширение: когда коэффициент
 *   загрузки size/capacity превышает LOAD_FACTOR (например 0.75), создайте
 *   массив корзин ВДВОЕ больше и перераспределите ВСЕ элементы (rehash).
 *   Это держит цепочки короткими и сохраняет O(1) в среднем.
 *
 * ПРИМЕР / ВЫВОД:
 *   начальная ёмкость 4
 *   после вставки 100 ключей: capacity выросла (>= ~170), get любого ключа корректен
 *   средняя длина цепочки осталась маленькой
 *
 * ТРЕБОВАНИЯ:
 *   - порог LOAD_FACTOR = 0.75; при превышении — resize (capacity *= 2);
 *   - rehash переносит все записи в новые корзины (индексы пересчитываются);
 *   - после многих вставок все ключи доступны через get.
 *
 * ПОДСКАЗКА:
 *   resize(): сохранить старые корзины; завести новый массив вдвое; пройти все
 *   старые записи и put-нуть их (или напрямую разложить) в новые корзины.
 */

public class Task03 {

    static class Entry { int key, value; Entry next; Entry(int k,int v){key=k;value=v;} }

    static class ResizableHashMap {
        static final double LOAD_FACTOR = 0.75;
        private Entry[] buckets = new Entry[4];
        private int size;

        private int indexFor(int key, int cap) { return (Integer.hashCode(key) & 0x7fffffff) % cap; }

        public void put(int key, int value) {
            // TODO: обычная вставка в цепочку; если size/capacity > LOAD_FACTOR -> resize()
        }
        public Integer get(int key) {
            for (Entry e = buckets[indexFor(key, buckets.length)]; e != null; e = e.next)
                if (e.key == key) return e.value;
            return null;
        }
        private void resize() {
            // TODO: новый массив вдвое; перенести все записи; заменить buckets
        }
        public int capacity() { return buckets.length; }
        public int size() { return size; }
    }

    public static void main(String[] args) {
        ResizableHashMap map = new ResizableHashMap();
        for (int i = 0; i < 100; i++) map.put(i, i * 10);
        System.out.println("capacity = " + map.capacity() + ", size = " + map.size());
        System.out.println("get(42) = " + map.get(42));
    }
}
