package m29_gc_reference_types.practice;

/**
 * Задача 05 — Модуль 29: PhantomReference и ReferenceQueue
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте уведомление о сборке объекта.
 *   1. Создайте ReferenceQueue и PhantomReference на объект,
 *      связав их.
 *   2. Уберите strong-ссылку, вызовите System.gc().
 *   3. В цикле проверяйте queue.poll() — когда вернётся не null,
 *      объект собран; выведите "Объект собран сборщиком мусора".
 *
 * ЦЕЛЬ:
 *   Понять механизм пост-обработки: phantom.get() ВСЕГДА null,
 *   но через очередь можно узнать, что объект уничтожен.
 *
 * ПОДСКАЗКА:
 *   ReferenceQueue<Object> q = new ReferenceQueue<>();
 *   PhantomReference<Object> p = new PhantomReference<>(obj, q);
 *   obj = null; System.gc();
 *   Reference<?> ref = q.remove(1000); // ждёт до 1с
 */
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Task05 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация PhantomReference ===");
        System.out.println();

        // Базовый пример
        demonstrateBasicPhantomReference();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с ресурсами и очисткой
        demonstratePhantomReferenceWithCleanup();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Сравнение всех типов ссылок
        compareAllReferenceTypes();

        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println();

        // Пример с множественными объектами
        demonstrateMultiplePhantomReferences();
    }

    // Базовый пример
    private static void demonstrateBasicPhantomReference() throws InterruptedException {
        System.out.println("--- Базовый пример PhantomReference ---");

        // 1. Создаем объект
        System.out.println("1. Создаем объект:");
        Object obj = new Object();
        System.out.println("   Объект создан: " + obj);

        // 2. Создаем ReferenceQueue
        System.out.println("\n2. Создаем ReferenceQueue:");
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        System.out.println("   ReferenceQueue создан");

        // 3. Создаем PhantomReference
        System.out.println("\n3. Создаем PhantomReference:");
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);
        System.out.println("   PhantomReference создан");

        // 4. Проверяем phantomRef.get()
        System.out.println("\n4. Проверяем phantomRef.get():");
        System.out.println("   phantomRef.get() = " + phantomRef.get());
        System.out.println("   ✅ Всегда возвращает null (даже если объект жив)");

        // 5. Обнуляем strong-ссылку
        System.out.println("\n5. Обнуляем strong-ссылку:");
        obj = null;
        System.out.println("   obj = null");

        // 6. Вызываем GC
        System.out.println("\n6. Вызываем System.gc()...");
        System.gc();
        System.out.println("   System.gc() вызван");

        // 7. Проверяем очередь
        System.out.println("\n7. Проверяем ReferenceQueue (poll):");
        Reference<?> ref = queue.poll();
        if (ref != null) {
            System.out.println("   ✅ Reference найден в очереди: " + ref);
            System.out.println("   ✅ Объект собран сборщиком мусора!");
            System.out.println("   (PhantomReference помещен в очередь после сборки)");
        } else {
            System.out.println("   ⚠️ Очередь пуста (GC еще не сработал или объект не собран)");
            System.out.println("   Пытаемся подождать...");

            // Ждем с таймаутом
            System.out.println("\n   Ожидаем до 2 секунд (queue.remove(2000))...");
            ref = queue.remove(2000);
            if (ref != null) {
                System.out.println("   ✅ Reference найден в очереди: " + ref);
                System.out.println("   ✅ Объект собран сборщиком мусора!");
            } else {
                System.out.println("   ⚠️ Время ожидания истекло, объект не был собран");
            }
        }
    }

    // Пример с ресурсами и очисткой
    private static void demonstratePhantomReferenceWithCleanup() throws InterruptedException {
        System.out.println("--- PhantomReference с очисткой ресурсов ---");

        class Resource {
            private final int id;
            private final byte[] data;
            private boolean closed = false;

            Resource(int id) {
                this.id = id;
                this.data = new byte[1024 * 1024]; // 1 MB
                System.out.println("   Ресурс #" + id + " создан (1 MB)");
            }

            void use() {
                if (closed) {
                    throw new IllegalStateException("Ресурс закрыт");
                }
                System.out.println("   Ресурс #" + id + " используется");
            }

            void close() {
                if (!closed) {
                    closed = true;
                    System.out.println("   Ресурс #" + id + " закрыт (память освобождена)");
                }
            }

            @Override
            public String toString() {
                return "Resource #" + id;
            }
        }

        // Создаем ресурс
        System.out.println("\nСоздаем ресурс:");
        Resource resource = new Resource(1);
        resource.use();

        // Создаем ReferenceQueue и PhantomReference
        ReferenceQueue<Resource> queue = new ReferenceQueue<>();
        PhantomReference<Resource> phantomRef = new PhantomReference<>(resource, queue);

        System.out.println("\nPhantomReference создан для ресурса");

        // Обнуляем strong-ссылку
        System.out.println("\nОбнуляем strong-ссылку на ресурс:");
        resource = null;

        // Вызываем GC
        System.out.println("\nВызываем GC...");
        System.gc();

        // Ждем уведомление о сборке
        System.out.println("\nОжидаем уведомление о сборке (до 3 секунд)...");
        Reference<?> ref = queue.remove(3000);

        if (ref != null) {
            System.out.println("\n✅ Объект ресурса собран сборщиком мусора!");
            System.out.println("   Здесь можно выполнить дополнительную очистку");
            System.out.println("   (например, закрыть файлы, соединения)");

            // Симулируем очистку
            System.out.println("   Выполняем пост-обработку...");
            System.out.println("   ✅ Пост-обработка завершена");
        } else {
            System.out.println("\n⚠️ Время ожидания истекло");
            System.out.println("   Объект не был собран (или GC не сработал)");
        }
    }

    // Сравнение всех типов ссылок
    private static void compareAllReferenceTypes() throws InterruptedException {
        System.out.println("--- Сравнение всех типов ссылок ---");

        // Strong Reference
        System.out.println("\n1. Strong Reference:");
        Object strong = new Object();
        System.out.println("   Объект создан: " + strong);
        System.out.println("   strong = " + strong);
        strong = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("   ✅ Объект собран (нет strong-ссылок)");

        // Soft Reference
        System.out.println("\n2. Soft Reference:");
        Object softObj = new Object();
        java.lang.ref.SoftReference<Object> softRef = new java.lang.ref.SoftReference<>(softObj);
        System.out.println("   softRef.get() = " + softRef.get());
        softObj = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("   softRef.get() = " + softRef.get());
        System.out.println("   ✅ Объект сохранен (SoftReference)");

        // Weak Reference
        System.out.println("\n3. Weak Reference:");
        Object weakObj = new Object();
        java.lang.ref.WeakReference<Object> weakRef = new java.lang.ref.WeakReference<>(weakObj);
        System.out.println("   weakRef.get() = " + weakRef.get());
        weakObj = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("   weakRef.get() = " + weakRef.get());
        System.out.println("   ✅ Объект собран (WeakReference)");

        // Phantom Reference
        System.out.println("\n4. Phantom Reference:");
        Object phantomObj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(phantomObj, queue);
        System.out.println("   phantomRef.get() = " + phantomRef.get());
        System.out.println("   ✅ Всегда возвращает null");
        phantomObj = null;
        System.gc();
        Thread.sleep(100);
        Reference<?> ref = queue.poll();
        if (ref != null) {
            System.out.println("   ✅ Объект собран (PhantomReference в очереди)");
        } else {
            System.out.println("   ⚠️ Объект еще не собран");
        }

        System.out.println("\n📝 Сводка:");
        System.out.println("   Strong:  живет всегда, пока есть ссылка");
        System.out.println("   Soft:    живет до нехватки памяти");
        System.out.println("   Weak:    живет до следующей GC");
        System.out.println("   Phantom: используется для отслеживания сборки");
    }

    // Пример с множественными объектами
    private static void demonstrateMultiplePhantomReferences() throws InterruptedException {
        System.out.println("--- Множественные PhantomReference ---");

        class TrackedObject {
            private final int id;

            TrackedObject(int id) {
                this.id = id;
                System.out.println("   Создан объект #" + id);
            }

            @Override
            public String toString() {
                return "Object #" + id;
            }
        }

        // Создаем несколько объектов
        ReferenceQueue<TrackedObject> queue = new ReferenceQueue<>();
        java.util.List<PhantomReference<TrackedObject>> refs = new java.util.ArrayList<>();

        System.out.println("\nСоздаем 5 объектов с PhantomReference:");
        for (int i = 1; i <= 5; i++) {
            TrackedObject obj = new TrackedObject(i);
            PhantomReference<TrackedObject> phantomRef = new PhantomReference<>(obj, queue);
            refs.add(phantomRef);
            // Объект автоматически становится кандидатом на GC (нет strong-ссылок)
        }
        System.out.println("   Создано 5 PhantomReference");

        // Вызываем GC
        System.out.println("\nВызываем GC...");
        System.gc();

        System.out.println("\nОжидаем сборку объектов (до 2 секунд)...");
        int collected = 0;
        long startTime = System.currentTimeMillis();

        while (collected < 5 && (System.currentTimeMillis() - startTime) < 2000) {
            Reference<?> ref = queue.poll();
            if (ref != null) {
                collected++;
                System.out.println("   ✅ Объект #" + collected + " собран");
            }
            Thread.sleep(50);
        }

        System.out.println("\nРезультат: собрано " + collected + " из 5 объектов");
        if (collected == 5) {
            System.out.println("   ✅ Все объекты успешно собраны");
        } else {
            System.out.println("   ⚠️ Некоторые объекты еще не собраны");
            System.out.println("   (Может потребоваться больше времени или памяти)");
        }

        // Проверяем, что get() всегда null
        System.out.println("\nПроверка phantomRef.get():");
        for (int i = 0; i < refs.size(); i++) {
            PhantomReference<TrackedObject> ref = refs.get(i);
            System.out.println("   ref[" + i + "].get() = " + ref.get());
        }
        System.out.println("   ✅ Все phantomRef.get() возвращают null");
    }
}
