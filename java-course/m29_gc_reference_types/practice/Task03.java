package m29_gc_reference_types.practice;

/**
 * Задача 03 — Модуль 29: SoftReference как кеш
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте SoftReference: объект живёт, пока хватает памяти.
 *   1. Создайте SoftReference на «тяжёлый» объект (например, большой
 *      массив byte[]).
 *   2. Выведите, доступен ли он через get() сразу после создания.
 *   3. (Опционально) попробуйте создавать много больших массивов в
 *      цикле, провоцируя нехватку памяти, и проверьте, не стал ли
 *      softRef.get() == null.
 *
 * ЦЕЛЬ:
 *   Понять отличие Soft от Weak: Soft держится дольше — до нехватки
 *   памяти. Это делает его пригодным для кешей.
 *
 * ПОДСКАЗКА:
 *   SoftReference<byte[]> cache = new SoftReference<>(new byte[5_000_000]);
 *   byte[] data = cache.get();  // null, только если память закончилась.
 */
import java.lang.ref.SoftReference;

public class Task03 {
    public static void main(String[] args) {
        // Создайте SoftReference на большой массив, проверьте get()
    }
}
