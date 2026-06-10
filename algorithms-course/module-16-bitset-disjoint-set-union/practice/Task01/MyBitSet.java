/**
 * Битовый массив на long[]: один бит на элемент.
 * Бит i: слово i>>6 (i/64), позиция i&63 (i%64).
 */
public class MyBitSet {

    private final long[] words;

    public MyBitSet(int nbits) {
        this.words = new long[(nbits + 63) >> 6];   // округление вверх до слов
    }

    /** Установить бит i в 1. */
    public void set(int i) {
        // TODO: words[i>>6] |= (1L << (i & 63))
    }

    /** Сбросить бит i в 0. */
    public void clear(int i) {
        // TODO: words[i>>6] &= ~(1L << (i & 63))
    }

    /** Установлен ли бит i. */
    public boolean get(int i) {
        // TODO: (words[i>>6] & (1L << (i & 63))) != 0
        return false;
    }

    /** Индекс следующего установленного бита, начиная с from (включительно), или -1. */
    public int nextSetBit(int from) {
        // TODO: пройти биты начиная с from, вернуть первый установленный (или -1)
        return -1;
    }

    /** Количество установленных битов. */
    public int cardinality() {
        int c = 0;
        for (long w : words) c += Long.bitCount(w);
        return c;
    }
}
