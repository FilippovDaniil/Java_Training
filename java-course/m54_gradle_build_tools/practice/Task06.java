package m54_gradle_build_tools.practice;

/**
 * Задача 06 — Модуль 54: Многомодульный Gradle-проект
 *
 * ЗАДАНИЕ:
 *   Создайте многомодульный проект platform с двумя подмодулями: core и app.
 *   app зависит от core.
 *
 *   Структура:
 *     platform/
 *       settings.gradle
 *       build.gradle         ← общие настройки для всех подпроектов
 *       core/
 *         build.gradle
 *         src/main/java/com/example/core/Greeter.java
 *       app/
 *         build.gradle
 *         src/main/java/com/example/app/Main.java
 *
 *   1) settings.gradle (корень):
 *        rootProject.name = 'platform'
 *        include 'core', 'app'
 *
 *   2) build.gradle (корень — применяем java ко всем подпроектам):
 *        subprojects {
 *            apply plugin: 'java'
 *            repositories { mavenCentral() }
 *            java {
 *                sourceCompatibility = JavaVersion.VERSION_17
 *                targetCompatibility = JavaVersion.VERSION_17
 *            }
 *        }
 *
 *   3) core/build.gradle (нет особых зависимостей):
 *        // пустой — настройки унаследованы из subprojects
 *
 *   4) core/src/main/java/com/example/core/Greeter.java:
 *        package com.example.core;
 *        public class Greeter {
 *            public String greet(String name) {
 *                return "Привет, " + name + "!";
 *            }
 *        }
 *
 *   5) app/build.gradle (зависит от core):
 *        dependencies {
 *            implementation project(':core')
 *        }
 *
 *   6) app/src/main/java/com/example/app/Main.java:
 *        package com.example.app;
 *        import com.example.core.Greeter;
 *        public class Main {
 *            public static void main(String[] args) {
 *                System.out.println(new Greeter().greet("Мир"));
 *            }
 *        }
 *
 *   7) Добавьте Gradle Wrapper в корень platform/:
 *        gradle wrapper --gradle-version 8.8
 *
 *   8) Выполните сборку из корня:
 *        .\gradlew.bat build
 *
 *   9) Проверьте сборку отдельного модуля:
 *        .\gradlew.bat :core:build
 *        .\gradlew.bat :app:build
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   BUILD SUCCESSFUL. Файлы:
 *     core/build/libs/core.jar
 *     app/build/libs/app.jar
 *   Gradle собирает core раньше app (автоматически по графу зависимостей).
 *
 * ПРОВЕРКА:
 *   java -cp "app/build/libs/app.jar;core/build/libs/core.jar" com.example.app.Main
 *   → Привет, Мир!
 *   (на Unix: используйте : вместо ; в classpath)
 *
 * ПОДСКАЗКА:
 *   project(':core') — ссылка на соседний подпроект по его пути в settings.gradle.
 *   Gradle автоматически определяет порядок сборки: сначала проекты,
 *   от которых зависят другие.
 */
public class Task06 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
    }
}
