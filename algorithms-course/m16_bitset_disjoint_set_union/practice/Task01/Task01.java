package m16_bitset_disjoint_set_union.practice.task01;

/**
 * Задача 01 — Тема 16: BitSet вручную (long[]) — set, get, nextSetBit
 *
 * ЗАДАНИЕ:
 *   Реализуйте MyBitSet (файл MyBitSet.java) на массиве long: set(i), clear(i),
 *   get(i), nextSetBit(from), cardinality(). Один бит на элемент.
 *
 * ПРИМЕР / ВЫВОД:
 *   set(1); set(3); set(4); set(64); set(130)
 *   get(3)   -> true,  get(2) -> false
 *   nextSetBit(0)  -> 1
 *   nextSetBit(5)  -> 64
 *   cardinality    -> 5
 *
 * ТРЕБОВАНИЯ:
 *   - использовать 1L (long!) при сдвигах, иначе биты >31 не установятся;
 *   - бит i: слово i>>6, позиция i&63;
 *   - nextSetBit корректно перескакивает через границы слов (64, 128, ...).
 *
 * ПОДСКАЗКА:
 *   nextSetBit можно делать побитово, но эффективнее — через Long.numberOfTrailingZeros
 *   на маскированном слове (необязательно для зачёта).
 */

public class Task01 {
    public static void main(String[] args) {
        MyBitSet bs = new MyBitSet(200);
        for (int i : new int[]{1, 3, 4, 64, 130}) bs.set(i);
        System.out.println("get(3) = " + bs.get(3));
        System.out.println("get(2) = " + bs.get(2));
        System.out.println("nextSetBit(0) = " + bs.nextSetBit(0));
        System.out.println("nextSetBit(5) = " + bs.nextSetBit(5));
        System.out.println("cardinality = " + bs.cardinality());
    }
}
