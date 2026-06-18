package m30_design_patterns.practice.task06;

/**
 * Задача 06 — Модуль 30: Proxy (заместитель)
 *
 * ЗАДАНИЕ:
 *   Реализуйте ленивую загрузку «тяжёлого» объекта через Proxy.
 *   1. Интерфейс Image с методом display().
 *   2. RealImage (тяжёлый объект): в конструкторе печатает
 *      "Загрузка <file> с диска..." (имитация дорогой операции),
 *      display() печатает "Показ <file>".
 *   3. ProxyImage implements Image: хранит имя файла, но создаёт
 *      RealImage ТОЛЬКО при первом вызове display() (ленивая загрузка),
 *      повторные вызовы display() не пересоздают объект.
 *   4. В main создайте ProxyImage (загрузки ещё нет!), затем дважды
 *      вызовите display() — загрузка произойдёт только один раз.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   (создание прокси — без загрузки)
 *   Загрузка photo.jpg с диска...
 *   Показ photo.jpg
 *   Показ photo.jpg
 *
 * ПОДСКАЗКА:
 *   В display(): if (realImage == null) realImage = new RealImage(file);
 *                realImage.display();
 */

public class Task06 {
    public static void main(String[] args) {
        System.out.println("=== Паттерн Proxy: Ленивая загрузка ===\n");

        // 1. Создаем прокси (без загрузки)
        System.out.println("1. Создаем ProxyImage для 'photo.jpg':");
        ProxyImage image1 = new ProxyImage("photo.jpg");

        // 2. Первый вызов display() - происходит загрузка
        System.out.println("\n2. Первый вызов display():");
        image1.display();

        // 3. Второй вызов display() - загрузка НЕ происходит
        System.out.println("\n3. Второй вызов display():");
        image1.display();

        // 4. Третий вызов display() - снова без загрузки
        System.out.println("\n4. Третий вызов display():");
        image1.display();

        // 5. Создаем еще один прокси
        System.out.println("\n5. Создаем ProxyImage для 'avatar.png':");
        ProxyImage image2 = new ProxyImage("avatar.png");

        // 6. Первый вызов для второго изображения
        System.out.println("\n6. Первый вызов display() для avatar.png:");
        image2.display();

        System.out.println("\n" + "=" .repeat(50));
        System.out.println("📊 Статистика:");
        System.out.println("   Изображение 1 (photo.jpg) - показано: " + image1.getDisplayCount() + " раз");
        System.out.println("   Изображение 2 (avatar.png) - показано: " + image2.getDisplayCount() + " раз");
        System.out.println("   Загрузка произошла только 2 раза (по одному на каждое изображение)");
    }
}