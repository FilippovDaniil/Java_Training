package m29_gc_reference_types.practice.task07;

/**
 * Задача 07 — Модуль 29 (МИНИ-ПРОЕКТ): Кеш на SoftReference
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой кеш изображений (имитация), который НЕ вызывает
 *   утечку памяти: значения хранятся через SoftReference, поэтому при
 *   нехватке памяти GC может их вытеснить.
 *
 *   Класс ImageCache:
 *     - Map<String, SoftReference<byte[]>> внутри;
 *     - put(String key, byte[] image) — кладёт значение в SoftReference;
 *     - byte[] get(String key) — возвращает значение или null, если
 *       оно было вытеснено сборщиком (тогда удалите «мёртвую» запись);
 *     - int size() — число записей.
 *   В main:
 *     - положите несколько «изображений» (большие массивы byte[]);
 *     - получите их обратно;
 *     - смоделируйте нагрузку на память и покажите, что часть значений
 *       может стать null (вытеснены), но кеш продолжает работать.
 *
 * ЦЕЛЬ:
 *   Понять практическое применение SoftReference: кеш, который сам
 *   «ужимается» под давлением памяти, не приводя к OutOfMemoryError.
 *
 * ПОДСКАЗКА:
 *   get(): SoftReference<byte[]> ref = map.get(key);
 *          return (ref != null) ? ref.get() : null;
 *   Если ref.get() == null — значение вытеснено, запись стоит удалить.
 */

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Task07 {
    public static void main(String[] args) {
        // Создайте ImageCache, наполните, проверьте get() и поведение под нагрузкой
    }
}
