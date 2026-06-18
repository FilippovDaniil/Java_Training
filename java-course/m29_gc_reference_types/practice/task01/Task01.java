package m29_gc_reference_types.practice.task01;

/**
 * Задача 01 — Модуль 29: Strong-ссылка и достижимость
 *
 * ЗАДАНИЕ:
 *   Создайте объект класса Resource (он печатает сообщение в
 *   конструкторе). Затем уберите ссылку (= null) и вызовите System.gc().
 *   Реализуйте у Resource метод-уведомление через переопределение
 *   метода finalize() ИЛИ просто понаблюдайте по логам, что объект
 *   стал недостижим.
 *
 * ЦЕЛЬ:
 *   Понять: пока есть strong-ссылка — объект жив; после null он
 *   становится кандидатом на сборку.
 *
 * ПОДСКАЗКА:
 *   System.gc() — лишь просьба к JVM. Сборка не гарантирована,
 *   поэтому момент удаления непредсказуем — это нормально.
 */

public class Task01 {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация Strong Reference (с Cleaner) ===");
        System.out.println();

        System.out.println("1. Создаем ресурс 'database_connection':");
        Resource resource1 = new Resource("database_connection");
        System.out.println("   Ресурс доступен: " + resource1.getName());
        resource1.use();

        System.out.println();

        System.out.println("2. Создаем ресурс 'file_handler':");
        Resource resource2 = new Resource("file_handler");
        System.out.println("   Ресурс доступен: " + resource2.getName());
        resource2.use();

        System.out.println();

        System.out.println("3. Обнуляем ссылку на 'database_connection' (resource1 = null):");
        resource1 = null;
        System.out.println("   Ссылка resource1 теперь null");

        System.out.println();

        System.out.println("4. Проверяем resource2 (должен быть доступен):");
        System.out.println("   Ресурс доступен: " + resource2.getName());
        resource2.use();

        System.out.println();

        System.out.println("5. Вызываем System.gc() - просим JVM запустить сборку мусора:");
        System.gc();

        System.out.println();

        System.out.println("6. Ожидаем возможное завершение сборки мусора...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();

        System.out.println("7. Проверяем состояние:");
        System.out.println("   Resource1 (database_connection) - ссылка обнулена, кандидат на сборку");
        System.out.println("   Resource2 (file_handler) - все еще доступен через strong-ссылку");
        System.out.println("   Если Cleaner отработал - вы увидите сообщение выше");

        System.out.println();

        System.out.println("=== Итог ===");
        System.out.println("Объекты с strong-ссылкой (resource2) остались доступны");
        System.out.println("Объекты без strong-ссылок (resource1) стали кандидатами на GC");
        System.out.println("Cleaner сообщит об удалении объекта при сборке мусора");
    }
}