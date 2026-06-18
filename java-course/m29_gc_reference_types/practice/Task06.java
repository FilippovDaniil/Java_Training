package m29_gc_reference_types.practice;

/**
 * Задача 06 — Модуль 29: Наблюдение за памятью
 *
 * ЗАДАНИЕ:
 *   Используя Runtime, измерьте использование памяти до и после
 *   создания большого количества объектов.
 *   1. Выведите занятую память (total - free).
 *   2. Создайте список из ~1_000_000 объектов (например, строк или
 *      массивов), удерживая их strong-ссылками.
 *   3. Снова выведите занятую память — она вырастет.
 *   4. Очистите список (clear или = null), вызовите System.gc(),
 *      выведите память ещё раз — она частично освободится.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Занято в начале: 5 МБ
 *   Занято после создания: 60 МБ
 *   Занято после очистки и GC: 7 МБ
 *
 * ПОДСКАЗКА:
 *   Runtime rt = Runtime.getRuntime();
 *   long used = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
 */
import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Наблюдение за памятью ===");
        System.out.println();

        // Базовый пример
        demonstrateMemoryObservation();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с разными типами объектов
        demonstrateMemoryWithDifferentObjects();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с мониторингом в реальном времени
        demonstrateRealTimeMemoryMonitoring();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Сравнение создания разных объектов
        compareMemoryUsageOfDifferentObjects();
    }

    // Базовый пример
    private static void demonstrateMemoryObservation() throws InterruptedException {
        System.out.println("--- Базовый пример наблюдения за памятью ---");

        Runtime runtime = Runtime.getRuntime();

        // 1. Память в начале
        System.out.println("\n1. Память в начале:");
        printMemoryUsage("Начало", runtime);

        // 2. Создаем список с объектами
        System.out.println("\n2. Создаем 1_000_000 объектов (строк):");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            list.add("String #" + i);
            if (i % 100_000 == 0 && i > 0) {
                System.out.println("   Создано " + i + " объектов...");
            }
        }
        System.out.println("   Создано 1_000_000 строк");

        // 3. Память после создания
        System.out.println("\n3. Память после создания:");
        printMemoryUsage("После создания", runtime);

        // 4. Очищаем список
        System.out.println("\n4. Очищаем список (list.clear()):");
        list.clear();
        System.out.println("   Список очищен");

        // 5. Вызываем GC
        System.out.println("\n5. Вызываем System.gc():");
        System.gc();
        System.out.println("   Ожидаем 500ms для завершения GC...");
        Thread.sleep(500);

        // 6. Память после очистки
        System.out.println("\n6. Память после очистки и GC:");
        printMemoryUsage("После очистки", runtime);

        // Вывод статистики
        System.out.println("\n📊 Статистика:");
        long usedBefore = getUsedMemory(runtime);
        printMemoryStats(runtime);
    }

    // Пример с разными типами объектов
    private static void demonstrateMemoryWithDifferentObjects() throws InterruptedException {
        System.out.println("--- Память для разных типов объектов ---");

        Runtime runtime = Runtime.getRuntime();

        // Создаем разные типы объектов
        System.out.println("\nСоздаем различные типы объектов:");

        // 1. Массив int[]
        System.out.println("\n1. Создаем 1_000_000 int значений:");
        printMemoryUsage("До int[]", runtime);
        List<int[]> intList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            intList.add(new int[100]); // 100 * 4 = 400 байт каждый
        }
        printMemoryUsage("После int[]", runtime);
        intList.clear();
        intList = null;
        System.gc();
        Thread.sleep(200);

        // 2. Массив byte[]
        System.out.println("\n2. Создаем 1_000_000 byte значений:");
        printMemoryUsage("До byte[]", runtime);
        List<byte[]> byteList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            byteList.add(new byte[100]); // 100 байт каждый
        }
        printMemoryUsage("После byte[]", runtime);
        byteList.clear();
        byteList = null;
        System.gc();
        Thread.sleep(200);

        // 3. Строки
        System.out.println("\n3. Создаем 1_000_000 строк:");
        printMemoryUsage("До String", runtime);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            stringList.add("String " + i);
        }
        printMemoryUsage("После String", runtime);
        stringList.clear();
        stringList = null;
        System.gc();
        Thread.sleep(200);

        System.out.println("\n📊 Итоговое состояние памяти:");
        printMemoryStats(runtime);
    }

    // Пример с мониторингом в реальном времени
    private static void demonstrateRealTimeMemoryMonitoring() throws InterruptedException {
        System.out.println("--- Мониторинг памяти в реальном времени ---");

        Runtime runtime = Runtime.getRuntime();
        List<byte[]> memoryHog = new ArrayList<>();

        System.out.println("\nЗапускаем мониторинг памяти (создание 100 блоков по 1 MB):");
        System.out.println("Начальное состояние:");
        printMemoryUsage("Старт", runtime);

        for (int i = 1; i <= 100; i++) {
            // Создаем блок памяти (1 MB)
            memoryHog.add(new byte[1024 * 1024]);

            // Каждые 10 блоков выводим состояние
            if (i % 10 == 0) {
                System.out.println("\nСоздано " + i + " блоков (1 MB каждый):");
                printMemoryUsage("Блок " + i, runtime);
            }
        }

        System.out.println("\nМаксимальное количество блоков: " + memoryHog.size());
        printMemoryUsage("После создания", runtime);

        // Постепенно освобождаем память
        System.out.println("\nОсвобождаем память (удаляем блоки):");
        for (int i = 100; i >= 10; i -= 10) {
            // Удаляем последние 10 блоков
            for (int j = 0; j < 10; j++) {
                if (!memoryHog.isEmpty()) {
                    memoryHog.remove(memoryHog.size() - 1);
                }
            }
            System.out.println("\nУдалено блоков до " + i + " (осталось " + memoryHog.size() + "):");
            printMemoryUsage("После удаления", runtime);

            Thread.sleep(100);
        }

        // Полная очистка
        System.out.println("\nПолная очистка:");
        memoryHog.clear();
        memoryHog = null;
        System.gc();
        Thread.sleep(500);

        printMemoryUsage("После полной очистки", runtime);
    }

    // Сравнение создания разных объектов
    private static void compareMemoryUsageOfDifferentObjects() throws InterruptedException {
        System.out.println("--- Сравнение памяти для разных типов ---");

        Runtime runtime = Runtime.getRuntime();
        int count = 100_000;

        System.out.println("\nСоздаем по " + count + " объектов разных типов:");

        // 1. Integer
        System.out.println("\n1. Integer объекты:");
        printMemoryUsage("До Integer", runtime);
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ints.add(i);
        }
        printMemoryUsage("После Integer", runtime);
        ints.clear();
        ints = null;
        System.gc();
        Thread.sleep(200);

        // 2. String (короткие)
        System.out.println("\n2. Короткие строки (5 символов):");
        printMemoryUsage("До коротких строк", runtime);
        List<String> shortStrings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            shortStrings.add("str" + i);
        }
        printMemoryUsage("После коротких строк", runtime);
        shortStrings.clear();
        shortStrings = null;
        System.gc();
        Thread.sleep(200);

        // 3. String (длинные)
        System.out.println("\n3. Длинные строки (100 символов):");
        printMemoryUsage("До длинных строк", runtime);
        List<String> longStrings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            longStrings.add("This is a very long string with many characters to consume more memory " + i);
        }
        printMemoryUsage("После длинных строк", runtime);
        longStrings.clear();
        longStrings = null;
        System.gc();
        Thread.sleep(200);

        // 4. byte[] arrays
        System.out.println("\n4. byte[] массивы (100 байт):");
        printMemoryUsage("До byte[]", runtime);
        List<byte[]> arrays = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arrays.add(new byte[100]);
        }
        printMemoryUsage("После byte[]", runtime);
        arrays.clear();
        arrays = null;
        System.gc();
        Thread.sleep(200);

        System.out.println("\n📊 Итоговое состояние:");
        printMemoryStats(runtime);

        System.out.println("\n📝 Выводы:");
        System.out.println("   - Integer (обертки) занимают больше памяти чем примитивы");
        System.out.println("   - Длинные строки занимают больше памяти");
        System.out.println("   - byte[] занимает меньше памяти чем Integer");
        System.out.println("   - Все объекты освобождаются после clear() и GC");
    }

    // Вспомогательные методы

    private static long getUsedMemory(Runtime runtime) {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static void printMemoryUsage(String label, Runtime runtime) {
        long used = getUsedMemory(runtime);
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long max = runtime.maxMemory();

        System.out.printf("   %-20s | Total: %6d MB | Free: %6d MB | Used: %6d MB | Max: %6d MB%n",
                label + ":",
                total / 1024 / 1024,
                free / 1024 / 1024,
                used / 1024 / 1024,
                max / 1024 / 1024);
    }

    private static void printMemoryStats(Runtime runtime) {
        long used = getUsedMemory(runtime);
        long total = runtime.totalMemory();
        long max = runtime.maxMemory();
        long free = runtime.freeMemory();

        System.out.println("   📊 Статистика памяти:");
        System.out.printf("   Общая память:   %d MB%n", total / 1024 / 1024);
        System.out.printf("   Свободно:        %d MB%n", free / 1024 / 1024);
        System.out.printf("   Используется:    %d MB%n", used / 1024 / 1024);
        System.out.printf("   Максимальная:    %d MB%n", max / 1024 / 1024);
        System.out.printf("   Использовано:    %.1f%%%n", (double) used / max * 100);
    }
}
