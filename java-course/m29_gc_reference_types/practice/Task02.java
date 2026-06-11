package m29_gc_reference_types.practice;

/**
 * Задача 02 — Модуль 29: WeakReference
 *
 * ЗАДАНИЕ:
 *   1. Создайте объект и оберните его в WeakReference.
 *   2. Выведите значение weak.get() — объект ещё жив.
 *   3. Уберите strong-ссылку на объект (= null), вызовите System.gc().
 *   4. Снова выведите weak.get() — скорее всего null (объект собран).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   До GC: Данные
 *   После GC: null
 *
 * ПОДСКАЗКА:
 *   WeakReference<String> weak = new WeakReference<>(strongRef);
 *   strongRef = null; System.gc();
 *   После System.gc() можно сделать небольшую паузу Thread.sleep(100).
 */
import java.lang.ref.WeakReference;

public class Task02 {
    public static void main(String[] args) throws InterruptedException {
        // Создайте WeakReference, обнулите strong-ссылку, вызовите GC
    }
}
