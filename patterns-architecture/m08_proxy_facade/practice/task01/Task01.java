package m08_proxy_facade.practice.task01;

/**
 * Задача 01 — Тема 08: Proxy (виртуальный, ленивая загрузка)
 *
 * ЗАДАНИЕ:
 *   Реализуйте ленивую загрузку «тяжёлого» объекта через прокси:
 *     - интерфейс Image (файл Image.java): void display();
 *     - RealImage (файл RealImage.java): конструктор печатает "загрузка <file>"
 *       (имитация дорогой операции), display() печатает "показ <file>";
 *     - ImageProxy (файл ImageProxy.java) реализует Image, хранит имя файла и
 *       создаёт RealImage ТОЛЬКО при первом display() (далее переиспользует).
 *   В main: создайте ImageProxy (загрузки быть НЕ должно), затем дважды вызовите
 *   display() — загрузка происходит один раз, при первом показе.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   прокси создан (загрузки ещё нет)
 *   загрузка photo.jpg
 *   показ photo.jpg
 *   показ photo.jpg
 *
 * ТРЕБОВАНИЯ:
 *   - создание ImageProxy НЕ создаёт RealImage;
 *   - RealImage создаётся при первом display() и затем не пересоздаётся;
 *   - ImageProxy и RealImage реализуют один интерфейс Image.
 *
 * ПОДСКАЗКА:
 *   if (real == null) real = new RealImage(file);  real.display();
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте ImageProxy, выведите что загрузки нет, дважды вызовите display()
    }
}
