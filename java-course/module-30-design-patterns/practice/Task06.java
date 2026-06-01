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
        // Создайте ProxyImage, дважды вызовите display() — загрузка один раз
    }
}

interface Image {
    void display();
}

// TODO: RealImage и ProxyImage implements Image (ленивая загрузка)
