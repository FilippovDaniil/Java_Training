/**
 * Задача 07 — Модуль 54 (МИНИ-ПРОЕКТ): Полноценная сборка приложения с плагином application
 *
 * ЗАДАНИЕ:
 *   Создайте одномодульный проект calculator-app с плагином 'application'.
 *   Плагин добавляет задачи: run, installDist, distZip, distTar.
 *   Цель — получить исполняемый дистрибутив, запускаемый без IDE.
 *
 *   Структура:
 *     calculator-app/
 *       settings.gradle
 *       build.gradle
 *       src/
 *         main/java/com/example/calc/
 *           Calculator.java       ← бизнес-логика (сложение, умножение)
 *           Main.java             ← точка входа
 *         test/java/com/example/calc/
 *           CalculatorTest.java   ← тесты JUnit 5
 *
 *   1) settings.gradle:
 *        rootProject.name = 'calculator-app'
 *
 *   2) build.gradle — напишите самостоятельно:
 *        plugins {
 *            id 'application'    // включает java + добавляет run / distZip / installDist
 *        }
 *        group   = 'com.example'
 *        version = '1.0.0'
 *        repositories { mavenCentral() }
 *        java {
 *            sourceCompatibility = JavaVersion.VERSION_17
 *            targetCompatibility = JavaVersion.VERSION_17
 *        }
 *        // Указываем главный класс — ОБЯЗАТЕЛЬНО для плагина application
 *        application {
 *            mainClass = 'com.example.calc.Main'
 *        }
 *        dependencies {
 *            testImplementation platform('org.junit:junit-bom:5.10.0')
 *            testImplementation 'org.junit.jupiter:junit-jupiter'
 *            testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
 *        }
 *        test {
 *            useJUnitPlatform()
 *        }
 *        // Кодировка — важно для Windows
 *        compileJava.options.encoding = 'UTF-8'
 *
 *   3) Calculator.java:
 *        package com.example.calc;
 *        public class Calculator {
 *            public int add(int a, int b) { return a + b; }
 *            public int multiply(int a, int b) { return a * b; }
 *        }
 *
 *   4) Main.java:
 *        package com.example.calc;
 *        public class Main {
 *            public static void main(String[] args) {
 *                Calculator calc = new Calculator();
 *                System.out.println("2 + 3 = " + calc.add(2, 3));
 *                System.out.println("4 * 5 = " + calc.multiply(4, 5));
 *            }
 *        }
 *
 *   5) CalculatorTest.java:
 *        package com.example.calc;
 *        import org.junit.jupiter.api.Test;
 *        import static org.junit.jupiter.api.Assertions.*;
 *        class CalculatorTest {
 *            Calculator calc = new Calculator();
 *            @Test void add()      { assertEquals(5, calc.add(2, 3)); }
 *            @Test void multiply() { assertEquals(20, calc.multiply(4, 5)); }
 *        }
 *
 *   6) Выполните команды последовательно:
 *        .\gradlew.bat test               — запустить тесты
 *        .\gradlew.bat run                — запустить приложение сразу через Gradle
 *        .\gradlew.bat installDist        — установить дистрибутив в build/install/
 *        .\gradlew.bat distZip            — упаковать в ZIP-архив
 *
 *   7) Запустите собранный дистрибутив напрямую (без Gradle):
 *        build\install\calculator-app\bin\calculator-app.bat    (Windows)
 *        build/install/calculator-app/bin/calculator-app        (Unix)
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   .\gradlew.bat run →
 *     > Task :run
 *     2 + 3 = 5
 *     4 * 5 = 20
 *     BUILD SUCCESSFUL
 *   Прямой запуск дистрибутива → тот же вывод, без Gradle.
 *   build/distributions/calculator-app-1.0.0.zip создан.
 *
 * ПОДСКАЗКА:
 *   Плагин 'application' включает 'java', поэтому не нужно дублировать
 *   id 'java' рядом с id 'application'.
 *   installDist создаёт папку bin/ со скриптами и lib/ со всеми jar.
 *   Именно это — рабочий способ поставки Java-приложения без Docker/uber-jar.
 */
public class Task07 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
    }
}
