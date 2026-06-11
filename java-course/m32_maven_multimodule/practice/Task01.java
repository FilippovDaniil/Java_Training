package m32_maven_multimodule.practice;

/**
 * Задача 01 — Модуль 32: Родительский POM (упражнение в терминале)
 *
 * Задачи модуля выполняются командами Maven и редактированием pom.xml.
 *
 * ЗАДАНИЕ:
 *   Создайте корневой (родительский) проект-агрегатор:
 *     1) папка my-platform с файлом pom.xml;
 *     2) в pom.xml: координаты (com.example : my-platform : 1.0.0)
 *        и packaging=pom;
 *     3) пока без модулей — просто валидный агрегатор.
 *
 * ФРАГМЕНТ pom.xml:
 *   <packaging>pom</packaging>
 *
 * ПРОВЕРКА:
 *   mvn validate в папке my-platform → BUILD SUCCESS.
 */
public class Task01 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
