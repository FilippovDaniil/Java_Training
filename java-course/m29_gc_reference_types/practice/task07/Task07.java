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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task07 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Кеш на SoftReference (ImageCache) ===");
        System.out.println();

        // Базовый тест
        testBasicOperations();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Тест с нагрузкой на память
        testMemoryPressure();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Тест с реальными данными
        testWithRealData();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Стресс-тест
        stressTest();
    }

    // Тест 1: Базовые операции
    private static void testBasicOperations() {
        System.out.println("--- Тест 1: Базовые операции ---");

        ImageCache cache = new ImageCache();

        // Создаем изображения
        System.out.println("\nСоздаем изображения:");
        byte[] img1 = new byte[1024 * 1024]; // 1 MB
        byte[] img2 = new byte[2 * 1024 * 1024]; // 2 MB
        byte[] img3 = new byte[512 * 1024]; // 512 KB

        // Добавляем в кеш
        System.out.println("\nДобавляем в кеш:");
        cache.put("image1.jpg", img1);
        cache.put("image2.png", img2);
        cache.put("image3.gif", img3);

        // Получаем изображения
        System.out.println("\nПолучаем изображения:");
        cache.get("image1.jpg");
        cache.get("image2.png");
        cache.get("image3.gif");

        // Проверяем несуществующее изображение
        cache.get("nonexistent.jpg");

        // Статистика
        cache.printStats();

        // Проверяем размер
        System.out.println("\nРазмер кеша: " + cache.size());
        System.out.println("Общий размер данных: " + cache.getTotalSize() / 1024 + " KB");
    }

    // Тест 2: Нагрузка на память
    private static void testMemoryPressure() throws InterruptedException {
        System.out.println("--- Тест 2: Нагрузка на память ---");

        ImageCache cache = new ImageCache();

        // Добавляем 10 больших изображений (по 5 MB)
        System.out.println("\nДобавляем 10 изображений по 5 MB:");
        for (int i = 1; i <= 10; i++) {
            byte[] img = new byte[5 * 1024 * 1024]; // 5 MB
            // Заполняем данными
            for (int j = 0; j < img.length; j++) {
                img[j] = (byte) (j % 256);
            }
            cache.put("image_" + i + ".jpg", img);
        }

        System.out.println("\nСостояние кеша после добавления:");
        cache.printStats();

        // Получаем некоторые изображения
        System.out.println("\nПолучаем несколько изображений:");
        for (int i = 1; i <= 5; i++) {
            cache.get("image_" + i + ".jpg");
        }

        // Создаем нагрузку на память
        System.out.println("\nСоздаем нагрузку на память (20 блоков по 10 MB)...");
        List<byte[]> memoryHog = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            System.out.println("   Выделяем блок #" + i + " (10 MB)");
            memoryHog.add(new byte[10 * 1024 * 1024]);

            // Проверяем состояние кеша каждые 5 блоков
            if (i % 5 == 0) {
                System.out.println("\n   Состояние кеша после " + i + " блоков:");
                System.out.println("   Размер кеша: " + cache.size());
                System.out.println("   Данных в кеше: " + cache.getTotalSize() / 1024 / 1024 + " MB");

                // Пытаемся получить изображения
                System.out.println("   Попытка получить image_1.jpg: " +
                        (cache.get("image_1.jpg") != null ? "ДОСТУПНО" : "ВЫТЕСНЕНО"));
                System.out.println("   Попытка получить image_5.jpg: " +
                        (cache.get("image_5.jpg") != null ? "ДОСТУПНО" : "ВЫТЕСНЕНО"));
                System.out.println("   Попытка получить image_10.jpg: " +
                        (cache.get("image_10.jpg") != null ? "ДОСТУПНО" : "ВЫТЕСНЕНО"));
            }

            // Небольшая пауза для GC
            Thread.sleep(100);
        }

        // Освобождаем память
        System.out.println("\nОсвобождаем память (очищаем нагрузку)...");
        memoryHog.clear();
        memoryHog = null;
        System.gc();
        Thread.sleep(1000);

        // Финальная статистика
        System.out.println("\nФинальное состояние кеша:");
        cache.printStats();

        // Проверяем, какие изображения остались
        System.out.println("\nПроверка доступных изображений:");
        for (int i = 1; i <= 10; i++) {
            byte[] img = cache.get("image_" + i + ".jpg");
            if (img != null) {
                System.out.println("   ✅ image_" + i + ".jpg доступно (" + img.length / 1024 + " KB)");
            }
        }
    }

    // Тест 3: Реальные данные
    private static void testWithRealData() {
        System.out.println("--- Тест 3: Реальные данные ---");

        ImageCache cache = new ImageCache();

        // Симулируем загрузку изображений разных размеров
        System.out.println("\nЗагружаем изображения разных размеров:");
        int[] sizes = {100, 200, 500, 1000, 2000, 5000}; // KB

        for (int size : sizes) {
            byte[] img = new byte[size * 1024];
            // Заполняем случайными данными
            for (int i = 0; i < img.length; i++) {
                img[i] = (byte) (Math.random() * 256);
            }
            cache.put("image_" + size + "KB.jpg", img);
        }

        cache.printStats();

        // Получаем все изображения
        System.out.println("\nПолучаем все изображения:");
        for (int size : sizes) {
            cache.get("image_" + size + "KB.jpg");
        }

        // Проверяем доступность
        System.out.println("\nПроверка доступности:");
        System.out.println("   image_5000KB.jpg: " +
                (cache.containsKey("image_5000KB.jpg") ? "✅ Доступно" : "❌ Вытеснено"));
        System.out.println("   image_100KB.jpg: " +
                (cache.containsKey("image_100KB.jpg") ? "✅ Доступно" : "❌ Вытеснено"));
    }

    // Стресс-тест
    private static void stressTest() throws InterruptedException {
        System.out.println("--- Стресс-тест кеша ---");

        ImageCache cache = new ImageCache();
        int totalImages = 50;
        int imageSize = 2 * 1024 * 1024; // 2 MB

        System.out.println("\nДобавляем " + totalImages + " изображений по 2 MB:");
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= totalImages; i++) {
            byte[] img = new byte[imageSize];
            // Быстрое заполнение
            for (int j = 0; j < img.length; j += 1024) {
                img[j] = (byte) (j % 256);
            }
            cache.put("img_" + i + ".dat", img);

            // Каждые 10 изображений выводим статус
            if (i % 10 == 0) {
                System.out.println("   Добавлено " + i + " изображений. Размер кеша: " + cache.size());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("   Время добавления: " + (endTime - startTime) + " ms");

        // Проверяем случайные изображения
        System.out.println("\nПроверка случайных изображений:");
        java.util.Random random = new java.util.Random();
        int hits = 0;
        int totalChecks = 20;

        for (int i = 0; i < totalChecks; i++) {
            int index = random.nextInt(totalImages) + 1;
            byte[] img = cache.get("img_" + index + ".dat");
            if (img != null) {
                hits++;
            }
        }

        System.out.println("   Попаданий: " + hits + " из " + totalChecks);
        System.out.println("   Процент попаданий: " + (double) hits / totalChecks * 100 + "%");

        cache.printStats();

        // Очищаем кеш
        System.out.println("\nОчищаем кеш:");
        cache.clear();
        System.out.println("   Размер после очистки: " + cache.size());
    }
}
