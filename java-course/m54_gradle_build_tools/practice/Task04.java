package m54_gradle_build_tools.practice;

/**
 * Задача 04 — Модуль 54: Конфигурации зависимостей (implementation / api / compileOnly / runtimeOnly)
 *
 * ЗАДАНИЕ:
 *   Создайте проект lib-demo с плагином 'java-library' и расставьте
 *   правильные конфигурации зависимостей:
 *
 *   1) Создайте папку lib-demo, добавьте wrapper и build.gradle:
 *
 *        plugins {
 *            id 'java-library'   // добавляет конфигурацию api (отличие от 'java')
 *        }
 *        group   = 'com.example'
 *        version = '1.0.0'
 *        repositories { mavenCentral() }
 *
 *   2) Добавьте зависимости с правильными конфигурациями:
 *
 *        dependencies {
 *            // api — тип из библиотеки входит в публичный API модуля
 *            // (потребители этого модуля увидят guava в своём compile classpath)
 *            api 'com.google.guava:guava:33.2.0-jre'
 *
 *            // implementation — внутренняя деталь реализации
 *            // (потребители НЕ видят commons-lang3)
 *            implementation 'org.apache.commons:commons-lang3:3.14.0'
 *
 *            // compileOnly — нужна только при компиляции, нет в runtime
 *            // типичный пример: Lombok, JSR-305 (@Nullable)
 *            compileOnly 'com.google.code.findbugs:jsr305:3.0.2'
 *
 *            // runtimeOnly — нужна только в runtime, не участвует в компиляции
 *            // типичный пример: JDBC-драйвер (код пишется против java.sql.*)
 *            runtimeOnly 'com.h2database:h2:2.2.224'
 *
 *            // testImplementation — только тесты
 *            testImplementation platform('org.junit:junit-bom:5.10.0')
 *            testImplementation 'org.junit.jupiter:junit-jupiter'
 *        }
 *
 *   3) Проверьте classpath-и:
 *        .\gradlew.bat dependencies --configuration compileClasspath
 *        .\gradlew.bat dependencies --configuration runtimeClasspath
 *
 *   4) Убедитесь: h2 есть в runtimeClasspath, но НЕ в compileClasspath.
 *      jsr305 есть в compileClasspath, но НЕ в runtimeClasspath.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   compileClasspath содержит: guava, commons-lang3, jsr305.
 *   runtimeClasspath содержит: guava, commons-lang3, h2.
 *   jsr305 отсутствует в runtimeClasspath.
 *   h2 отсутствует в compileClasspath.
 *
 * ПОДСКАЗКА:
 *   Правило выбора конфигурации:
 *     - тип зависимости в public API → api
 *     - внутренняя деталь реализации → implementation
 *     - только аннотации / кодогенерация → compileOnly
 *     - только runtime (драйверы, реализации SPI) → runtimeOnly
 */
public class Task04 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
    }
}
