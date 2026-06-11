package m32_maven_multimodule.practice;

/**
 * Задача 03 — Модуль 32: Зависимость между модулями (упражнение)
 *
 * ЗАДАНИЕ:
 *   Сделайте так, чтобы модуль app использовал код из модуля common.
 *     1) в common создайте класс-утилиту (например, Greeting с методом
 *        message());
 *     2) в pom.xml модуля app добавьте зависимость на common;
 *     3) в классе app вызовите метод из common;
 *     4) соберите проект из корня — реактор должен собрать common ПЕРЕД app.
 *
 * ФРАГМЕНТ app/pom.xml:
 *   <dependency>
 *       <groupId>com.example</groupId>
 *       <artifactId>common</artifactId>
 *       <version>1.0.0</version>
 *   </dependency>
 *
 * ПРОВЕРКА:
 *   mvn clean install в корне → BUILD SUCCESS; в логе видно, что
 *   common собирается раньше app.
 */
public class Task03 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
