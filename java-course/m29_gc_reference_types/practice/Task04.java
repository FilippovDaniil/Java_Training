package m29_gc_reference_types.practice;

/**
 * Задача 04 — Модуль 29: WeakHashMap
 *
 * ЗАДАНИЕ:
 *   Покажите автоматическое удаление записей из WeakHashMap.
 *   1. Создайте WeakHashMap, положите 2-3 записи, где ключи — объекты,
 *      на которые есть strong-ссылки (переменные).
 *   2. Выведите размер карты.
 *   3. Обнулите strong-ссылки на часть ключей, вызовите System.gc(),
 *      сделайте паузу.
 *   4. Снова выведите размер — записи с собранными ключами исчезнут.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Размер до GC: 3
 *   Размер после GC: 1
 *
 * ПОДСКАЗКА:
 *   Map<Object, String> map = new WeakHashMap<>();
 *   Сохраните один ключ в переменной (его запись не исчезнет),
 *   остальные обнулите.
 */
import java.util.Map;
import java.util.WeakHashMap;

public class Task04 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация WeakHashMap ===");
        System.out.println();

        // Базовый пример
        demonstrateBasicWeakHashMap();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с разными типами ключей
        demonstrateWeakHashMapWithDifferentKeys();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Сравнение с обычным HashMap
        compareWeakHashMapWithHashMap();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример использования в кеше
        demonstrateWeakHashMapAsCache();
    }

    // Базовый пример
    private static void demonstrateBasicWeakHashMap() throws InterruptedException {
        System.out.println("--- Базовый пример WeakHashMap ---");

        // Создаем WeakHashMap
        Map<Object, String> weakMap = new WeakHashMap<>();
        System.out.println("Создан WeakHashMap");

        // 1. Создаем ключи с strong-ссылками
        System.out.println("\n1. Добавляем 3 записи:");
        Object key1 = new Object();
        Object key2 = new Object();
        Object key3 = new Object();

        weakMap.put(key1, "Значение 1");
        weakMap.put(key2, "Значение 2");
        weakMap.put(key3, "Значение 3");

        System.out.println("   Добавлены записи: key1, key2, key3");
        System.out.println("   Размер карты: " + weakMap.size());
        System.out.println("   Содержимое: " + weakMap);

        // 2. Обнуляем strong-ссылки на часть ключей
        System.out.println("\n2. Обнуляем strong-ссылки на key1 и key2:");
        key1 = null;
        key2 = null;
        System.out.println("   key1 = null, key2 = null");
        System.out.println("   key3 все еще имеет strong-ссылку");

        // 3. Вызываем GC
        System.out.println("\n3. Вызываем System.gc()...");
        System.gc();
        System.out.println("   Ожидаем 200ms для завершения GC...");
        Thread.sleep(200);

        // 4. Проверяем размер после GC
        System.out.println("\n4. Проверяем размер после GC:");
        System.out.println("   Размер карты: " + weakMap.size());
        System.out.println("   Содержимое: " + weakMap);

        if (weakMap.size() == 1) {
            System.out.println("   ✅ Записи с key1 и key2 удалены (их ключи были собраны GC)");
            System.out.println("   ✅ Запись с key3 сохранилась (strong-ссылка существует)");
        } else if (weakMap.size() == 3) {
            System.out.println("   ⚠️ Записи не удалены (GC не сработал или ключи все еще достижимы)");
        } else {
            System.out.println("   ℹ️ Размер: " + weakMap.size() + " (частичное удаление)");
        }
    }

    // Пример с разными типами ключей
    private static void demonstrateWeakHashMapWithDifferentKeys() throws InterruptedException {
        System.out.println("--- WeakHashMap с разными типами ключей ---");

        Map<Object, String> weakMap = new WeakHashMap<>();

        // Используем разные типы ключей
        System.out.println("Создаем ключи разных типов:");
        String stringKey = new String("StringKey"); // Не использовать литералы!
        Integer intKey = Integer.valueOf(42);
        Object objKey = new Object();
        byte[] arrayKey = new byte[1024];

        weakMap.put(stringKey, "Строковый ключ");
        weakMap.put(intKey, "Целочисленный ключ");
        weakMap.put(objKey, "Объектный ключ");
        weakMap.put(arrayKey, "Массив ключ");

        System.out.println("Размер до GC: " + weakMap.size());
        System.out.println("Ключи: [String, Integer, Object, byte[]]");

        // Обнуляем некоторые ключи
        System.out.println("\nОбнуляем strong-ссылки на StringKey и ObjectKey:");
        stringKey = null;
        objKey = null;

        System.out.println("Вызываем GC...");
        System.gc();
        Thread.sleep(200);

        System.out.println("\nРазмер после GC: " + weakMap.size());
        System.out.println("Оставшиеся ключи:");
        for (Object key : weakMap.keySet()) {
            String keyType = key.getClass().getSimpleName();
            System.out.println("  - " + keyType + ": " + key);
        }

        if (weakMap.size() == 2) {
            System.out.println("\n✅ Остались только Integer и byte[] ключи");
            System.out.println("   (String и Object были собраны)");
        }
    }

    // Сравнение с обычным HashMap
    private static void compareWeakHashMapWithHashMap() throws InterruptedException {
        System.out.println("--- Сравнение WeakHashMap и HashMap ---");

        // WeakHashMap
        System.out.println("\n1. WeakHashMap:");
        Map<Object, String> weakMap = new WeakHashMap<>();
        Object weakKey1 = new Object();
        Object weakKey2 = new Object();
        weakMap.put(weakKey1, "weak-1");
        weakMap.put(weakKey2, "weak-2");

        System.out.println("   До GC: size = " + weakMap.size());
        weakKey1 = null;
        System.gc();
        Thread.sleep(200);
        System.out.println("   После GC: size = " + weakMap.size());
        System.out.println("   ✅ Записи с обнуленными ключами удалены");

        // HashMap
        System.out.println("\n2. HashMap:");
        Map<Object, String> hashMap = new java.util.HashMap<>();
        Object hashKey1 = new Object();
        Object hashKey2 = new Object();
        hashMap.put(hashKey1, "hash-1");
        hashMap.put(hashKey2, "hash-2");

        System.out.println("   До GC: size = " + hashMap.size());
        hashKey1 = null;
        System.gc();
        Thread.sleep(200);
        System.out.println("   После GC: size = " + hashMap.size());
        System.out.println("   ✅ Записи НЕ удалены (HashMap держит сильные ссылки на ключи)");

        System.out.println("\n📝 Вывод:");
        System.out.println("   - HashMap: хранит strong-ссылки на ключи");
        System.out.println("   - WeakHashMap: хранит weak-ссылки на ключи");
        System.out.println("   - WeakHashMap автоматически удаляет записи при GC");
    }

    // Пример использования в кеше
    private static void demonstrateWeakHashMapAsCache() throws InterruptedException {
        System.out.println("--- WeakHashMap как кеш ---");

        class DataCache {
            private final Map<Object, byte[]> cache = new WeakHashMap<>();
            private int hits = 0;
            private int misses = 0;

            byte[] getData(Object key) {
                byte[] data = cache.get(key);
                if (data != null) {
                    hits++;
                    System.out.println("  ✅ Кеш HIT для " + key);
                } else {
                    misses++;
                    System.out.println("  ❌ Кеш MISS для " + key + " (загружаем данные)");
                    // Симулируем загрузку данных
                    data = new byte[1024 * 1024]; // 1 MB
                    cache.put(key, data);
                }
                return data;
            }

            void printStats() {
                System.out.println("  Статистика кеша: Hits=" + hits + ", Misses=" + misses);
                System.out.println("  Размер кеша: " + cache.size());
            }
        }

        DataCache cache = new DataCache();

        // Создаем ключи
        System.out.println("Создаем кеш с данными...");
        Object key1 = new Object();
        Object key2 = new Object();
        Object key3 = new Object();

        // Загружаем данные в кеш
        cache.getData(key1);
        cache.getData(key2);
        cache.getData(key3);
        cache.printStats();

        // Используем кеш несколько раз
        System.out.println("\nИспользуем кеш:");
        cache.getData(key1);
        cache.getData(key2);
        cache.getData(key1);
        cache.printStats();

        // Обнуляем некоторые ключи
        System.out.println("\nОбнуляем strong-ссылки на key1 и key2:");
        key1 = null;
        key2 = null;

        System.out.println("Вызываем GC...");
        System.gc();
        Thread.sleep(200);

        System.out.println("\nПроверяем кеш после GC:");
        cache.printStats();

        // Пытаемся получить данные по новому ключу
        Object newKey = new Object();
        System.out.println("\nЗапрашиваем данные по новому ключу:");
        cache.getData(newKey);
        cache.printStats();

        System.out.println("\n📝 Вывод: WeakHashMap автоматически очищает кеш");
        System.out.println("   при нехватке памяти или после GC");
        System.out.println("   Это предотвращает утечки памяти в кешах");
    }
}
