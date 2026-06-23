package m54_gradle_build_tools.practice;

/**
 * Задача 02 — Модуль 54: Написать build.gradle с плагином java
 *
 * ЗАДАНИЕ:
 *   Создайте рабочий Gradle-проект с нуля и выполните полную сборку:
 *
 *   1) Создайте папку hello-gradle со структурой:
 *        hello-gradle/
 *          settings.gradle
 *          build.gradle
 *          src/main/java/Hello.java
 *
 *   2) settings.gradle:
 *        rootProject.name = 'hello-gradle'
 *
 *   3) build.gradle — напишите самостоятельно:
 *        plugins {
 *            id 'java'
 *        }
 *        group   = 'com.example'
 *        version = '1.0.0'
 *        repositories {
 *            mavenCentral()
 *        }
 *        java {
 *            sourceCompatibility = JavaVersion.VERSION_17
 *            targetCompatibility = JavaVersion.VERSION_17
 *        }
 *
 *   4) src/main/java/Hello.java — минимальный класс:
 *        public class Hello {
 *            public static void main(String[] args) {
 *                System.out.println("Hello, Gradle!");
 *            }
 *        }
 *
 *   5) Добавьте Gradle Wrapper (если ещё нет):
 *        gradle wrapper --gradle-version 8.8
 *
 *   6) Выполните сборку:
 *        .\gradlew.bat build
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   BUILD SUCCESSFUL in Xs
 *   Файл build/libs/hello-gradle-1.0.0.jar создан.
 *   Задачи в логе: compileJava, processResources, classes, jar, check, build.
 *
 * ПРОВЕРКА:
 *   java -cp build/libs/hello-gradle-1.0.0.jar Hello
 *   → выведет: Hello, Gradle!
 *
 * ПОДСКАЗКА:
 *   Задача jar создаёт архив автоматически при наличии плагина 'java'.
 *   Имя jar формируется как: ${project.name}-${project.version}.jar.
 */
public class Task02 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
        System.out.println("Done");
    }
}
