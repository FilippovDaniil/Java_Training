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
        System.out.println("=== Демонстрация WeakReference ===");
        System.out.println();

        // 1. Создаем strong-ссылку на объект
        System.out.println("1. Создаем объект с strong-ссылкой:");
        String strongRef = new String("Данные в строке");
        System.out.println("   strongRef = " + strongRef);

        // 2. Создаем WeakReference, который ссылается на тот же объект
        System.out.println();
        System.out.println("2. Создаем WeakReference для этого объекта:");
        WeakReference<String> weak = new WeakReference<>(strongRef);
        System.out.println("   WeakReference создан");

        // 3. Проверяем, что объект жив (до GC)
        System.out.println();
        System.out.println("3. Проверяем объект до GC:");
        System.out.println("   weak.get() = " + weak.get());
        System.out.println("   strongRef = " + strongRef);
        System.out.println("   Объект жив (strong-ссылка существует)");

        // 4. Убираем strong-ссылку
        System.out.println();
        System.out.println("4. Убираем strong-ссылку (strongRef = null):");
        strongRef = null;
        System.out.println("   strongRef теперь null");
        System.out.println("   Теперь объект достижим только через WeakReference");

        // 5. Проверяем, что объект все еще доступен через WeakReference
        System.out.println();
        System.out.println("5. Проверяем объект после обнуления strong-ссылки, но до GC:");
        System.out.println("   weak.get() = " + weak.get());
        System.out.println("   Объект все еще доступен через WeakReference");

        // 6. Вызываем сборку мусора
        System.out.println();
        System.out.println("6. Вызываем System.gc() для запуска сборки мусора:");
        System.gc();
        System.out.println("   System.gc() вызван");

        // 7. Ждем небольшое время для завершения GC
        System.out.println();
        System.out.println("7. Ожидаем завершения сборки мусора (100ms)...");
        Thread.sleep(100);

        // 8. Проверяем объект после GC
        System.out.println();
        System.out.println("8. Проверяем объект ПОСЛЕ GC:");
        System.out.println("   weak.get() = " + weak.get());

        if (weak.get() == null) {
            System.out.println("   ✅ Объект был собран сборщиком мусора!");
            System.out.println("   (WeakReference позволяет GC удалить объект)");
        } else {
            System.out.println("   ⚠️ Объект все еще жив (GC не запустился или объект не был собран)");
            System.out.println("   (Это нормально, т.к. System.gc() - только просьба к JVM)");
        }

        System.out.println();
        System.out.println("=== Итог ===");
        System.out.println("WeakReference не препятствует сборке мусора");
        System.out.println("Если объект достижим только через WeakReference, он может быть удален");
        System.out.println("При следующей сборке мусора weak.get() вернет null");
    }
}
