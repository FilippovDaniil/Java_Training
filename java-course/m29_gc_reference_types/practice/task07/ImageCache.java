package m29_gc_reference_types.practice.task07;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

class ImageCache {
    private final Map<String, SoftReference<byte[]>> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;
    private int evictedCount = 0;

    // Сохраняет изображение в кеш
    public void put(String key, byte[] image) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        cache.put(key, new SoftReference<>(image));
        System.out.println("   📥 Кеш: добавлено изображение '" + key + "' (" + image.length + " байт)");
    }

    // Возвращает изображение из кеша или null, если оно было вытеснено
    public byte[] get(String key) {
        if (key == null) {
            return null;
        }

        SoftReference<byte[]> ref = cache.get(key);
        if (ref == null) {
            misses++;
            System.out.println("   ❌ Кеш MISS: '" + key + "' (запись отсутствует)");
            return null;
        }

        byte[] image = ref.get();
        if (image == null) {
            // Значение было вытеснено GC - удаляем "мертвую" запись
            cache.remove(key);
            evictedCount++;
            misses++;
            System.out.println("   🗑️ Кеш EVICTED: '" + key + "' (вытеснено сборщиком мусора)");
            return null;
        }

        hits++;
        System.out.println("   ✅ Кеш HIT: '" + key + "' (" + image.length + " байт)");
        return image;
    }

    // Возвращает количество записей в кеше
    public int size() {
        // Очищаем мертвые записи перед подсчетом
        cleanDeadEntries();
        return cache.size();
    }

    // Очищает записи, значения которых были вытеснены
    public void cleanDeadEntries() {
        int before = cache.size();
        cache.entrySet().removeIf(entry -> entry.getValue().get() == null);
        int removed = before - cache.size();
        if (removed > 0) {
            System.out.println("   🧹 Очищено " + removed + " мертвых записей");
        }
    }

    // Возвращает статистику кеша
    public void printStats() {
        System.out.println("\n📊 Статистика кеша:");
        System.out.println("   Записей в кеше: " + size());
        System.out.println("   Хиты (Hits): " + hits);
        System.out.println("   Промахи (Misses): " + misses);
        System.out.println("   Вытеснено (Evicted): " + evictedCount);
        System.out.println("   Эффективность: " + getHitRate() + "%");
    }

    // Возвращает процент попаданий
    public double getHitRate() {
        int total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total * 100;
    }

    // Очищает весь кеш
    public void clear() {
        cache.clear();
        System.out.println("   🧹 Кеш полностью очищен");
    }

    // Возвращает общий размер данных в кеше (в байтах)
    public long getTotalSize() {
        long total = 0;
        for (SoftReference<byte[]> ref : cache.values()) {
            byte[] data = ref.get();
            if (data != null) {
                total += data.length;
            }
        }
        return total;
    }

    // Проверяет, есть ли ключ в кеше
    public boolean containsKey(String key) {
        if (key == null) return false;
        SoftReference<byte[]> ref = cache.get(key);
        return ref != null && ref.get() != null;
    }

    // Удаляет запись по ключу
    public void remove(String key) {
        if (key != null) {
            cache.remove(key);
            System.out.println("   🗑️ Удалена запись '" + key + "'");
        }
    }
}