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
import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация SoftReference ===");
        System.out.println();

        // Базовый пример
        demonstrateBasicSoftReference();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с провокацией нехватки памяти
        demonstrateSoftReferenceWithMemoryPressure();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Сравнение Soft vs Weak
        compareSoftVsWeak();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример использования как кеш
        demonstrateCacheUsage();
    }

    // Базовый пример
    private static void demonstrateBasicSoftReference() {
        System.out.println("--- Базовый пример SoftReference ---");

        // Создаем большой объект (5 MB)
        System.out.println("Создаем большой массив (5 MB)...");
        byte[] bigArray = new byte[5_000_000];

        // Заполняем массив данными
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (byte) (i % 256);
        }
        System.out.println("Массив создан и заполнен");

        // Создаем SoftReference
        SoftReference<byte[]> softRef = new SoftReference<>(bigArray);
        System.out.println("SoftReference создан");

        // Проверяем доступность
        System.out.println("До обнуления strong-ссылки:");
        System.out.println("  softRef.get() != null: " + (softRef.get() != null));
        System.out.println("  Размер массива: " + softRef.get().length + " байт");

        // Обнуляем strong-ссылку
        bigArray = null;
        System.out.println("\nstrong-ссылка обнулена");

        // Проверяем после обнуления
        System.out.println("После обнуления strong-ссылки:");
        if (softRef.get() != null) {
            System.out.println("  ✅ Объект все еще доступен через SoftReference");
            System.out.println("  Размер массива: " + softRef.get().length + " байт");
        } else {
            System.out.println("  ⚠️ Объект уже собран (недостаточно памяти)");
        }

        // Вызываем GC (но SoftReference должен сохранить объект)
        System.out.println("\nВызываем System.gc()...");
        System.gc();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("После System.gc():");
        if (softRef.get() != null) {
            System.out.println("  ✅ Объект все еще доступен (SoftReference устойчив к GC)");
            System.out.println("  Размер: " + softRef.get().length + " байт");
        } else {
            System.out.println("  ⚠️ Объект собран (память уже была нужна)");
        }
    }

    // Пример с провокацией нехватки памяти
    private static void demonstrateSoftReferenceWithMemoryPressure() {
        System.out.println("--- SoftReference при нехватке памяти ---");

        // Создаем SoftReference на большой объект (10 MB)
        System.out.println("Создаем SoftReference на массив 10 MB...");
        SoftReference<byte[]> softRef = new SoftReference<>(new byte[10_000_000]);
        System.out.println("SoftReference создан");
        System.out.println("Текущий статус: softRef.get() != null: " + (softRef.get() != null));

        if (softRef.get() == null) {
            System.out.println("⚠️ Объект уже собран (мало памяти)");
            return;
        }

        System.out.println("\nСоздаем много больших массивов для провокации нехватки памяти...");
        System.out.println("(Может занять некоторое время и вызвать OutOfMemoryError)");

        List<byte[]> arrays = new ArrayList<>();
        int count = 0;
        boolean memoryExhausted = false;

        try {
            // Создаем большие массивы до нехватки памяти
            while (true) {
                byte[] newArray = new byte[5_000_000]; // 5 MB
                arrays.add(newArray);
                count++;

                if (count % 5 == 0) {
                    System.out.println("  Создано " + count + " массивов по 5 MB");

                    // Проверяем состояние SoftReference
                    if (softRef.get() == null) {
                        System.out.println("  ⚠️ SoftReference стал null!");
                        break;
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("\n❌ OutOfMemoryError при создании " + count + " массивов!");
            memoryExhausted = true;
        }

        System.out.println("\nРезультат:");
        if (softRef.get() == null) {
            System.out.println("  ✅ SoftReference вернул null (объект был удален для освобождения памяти)");
            System.out.println("  Это подтверждает, что SoftReference освобождает объекты");
            System.out.println("  при нехватке памяти");
        } else {
            System.out.println("  ℹ️ SoftReference все еще содержит объект");
            System.out.println("  (Память еще не исчерпана или GC не сработал)");
        }

        if (memoryExhausted) {
            System.out.println("\n  Количество созданных массивов: " + count);
            System.out.println("  Общий объем: ~" + (count * 5) + " MB");
        }

        // Очищаем созданные массивы
        arrays.clear();
        System.gc();
        System.out.println("\nСозданные массивы очищены");
    }

    // Сравнение Soft vs Weak
    private static void compareSoftVsWeak() {
        System.out.println("--- Сравнение SoftReference и WeakReference ---");

        // SoftReference
        System.out.println("1. SoftReference:");
        byte[] softData = new byte[2_000_000];
        SoftReference<byte[]> softRef = new SoftReference<>(softData);
        softData = null;

        System.out.println("   До GC: softRef.get() != null: " + (softRef.get() != null));
        System.gc();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        System.out.println("   После GC: softRef.get() != null: " + (softRef.get() != null));
        System.out.println("   ✅ SoftReference держит объект даже после GC (пока есть память)");

        System.out.println();

        // WeakReference
        System.out.println("2. WeakReference:");
        byte[] weakData = new byte[2_000_000];
        java.lang.ref.WeakReference<byte[]> weakRef = new java.lang.ref.WeakReference<>(weakData);
        weakData = null;

        System.out.println("   До GC: weakRef.get() != null: " + (weakRef.get() != null));
        System.gc();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        System.out.println("   После GC: weakRef.get() != null: " + (weakRef.get() != null));
        System.out.println("   ✅ WeakReference освобождает объект сразу при GC");

        System.out.println("\n📝 Вывод:");
        System.out.println("   - WeakReference: объект удаляется при первой же GC");
        System.out.println("   - SoftReference: объект сохраняется до нехватки памяти");
        System.out.println("   - SoftReference лучше подходит для кешей");
    }

    // Пример использования как кеш
    private static void demonstrateCacheUsage() {
        System.out.println("--- Пример использования SoftReference как кеш ---");

        class CacheManager {
            private final SoftReference<byte[]> cache;
            private final String key;

            CacheManager(String key, byte[] data) {
                this.key = key;
                this.cache = new SoftReference<>(data);
                System.out.println("  Кеш создан для ключа: " + key);
            }

            byte[] getData() {
                byte[] data = cache.get();
                if (data == null) {
                    System.out.println("  ⚠️ Данные для ключа '" + key + "' выгружены из кеша");
                    System.out.println("  (Недостаточно памяти)");
                    return null;
                }
                System.out.println("  ✅ Данные для ключа '" + key + "' доступны в кеше");
                return data;
            }

            boolean isCached() {
                return cache.get() != null;
            }
        }

        // Создаем кеш с большим объектом
        System.out.println("Создаем кеш с данными (5 MB)...");
        byte[] data = new byte[5_000_000];
        CacheManager cache = new CacheManager("image1", data);
        data = null; // Убираем strong-ссылку

        // Проверяем кеш
        System.out.println("\nПроверка кеша:");
        byte[] cachedData = cache.getData();

        if (cachedData != null) {
            System.out.println("  Длина данных: " + cachedData.length + " байт");
        }

        // Создаем нагрузку на память
        System.out.println("\nСоздаем нагрузку на память...");
        List<byte[]> tempArrays = new ArrayList<>();
        try {
            for (int i = 0; i < 20; i++) {
                tempArrays.add(new byte[5_000_000]);
                if (i % 5 == 4) {
                    System.out.println("  Создано " + (i + 1) + " массивов");
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("  Нехватка памяти!");
        }

        // Проверяем кеш снова
        System.out.println("\nПроверка кеша после нагрузки:");
        if (cache.isCached()) {
            System.out.println("  ✅ Данные все еще в кеше (памяти достаточно)");
        } else {
            System.out.println("  ⚠️ Данные выгружены из кеша (была нехватка памяти)");
            System.out.println("  ✅ Это ожидаемое поведение SoftReference кеша");
        }

        // Очищаем память
        tempArrays.clear();
        System.gc();
    }
}
