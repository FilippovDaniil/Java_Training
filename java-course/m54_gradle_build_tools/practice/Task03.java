package m54_gradle_build_tools.practice;

/**
 * Задача 03 — Модуль 54: Добавление зависимостей и просмотр dependency tree
 *
 * ЗАДАНИЕ:
 *   Продолжите проект из Task02 (hello-gradle) — добавьте JUnit 5 и проверьте
 *   дерево зависимостей:
 *
 *   1) В build.gradle добавьте зависимости:
 *
 *        dependencies {
 *            // JUnit 5 BOM управляет версиями всех junit-модулей
 *            testImplementation platform('org.junit:junit-bom:5.10.0')
 *            testImplementation 'org.junit.jupiter:junit-jupiter'
 *            testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
 *        }
 *        test {
 *            useJUnitPlatform()
 *        }
 *
 *   2) Создайте src/test/java/HelloTest.java:
 *        import org.junit.jupiter.api.Test;
 *        import static org.junit.jupiter.api.Assertions.*;
 *        class HelloTest {
 *            @Test
 *            void sanity() {
 *                assertEquals(4, 2 + 2);
 *            }
 *        }
 *
 *   3) Запустите тесты:
 *        .\gradlew.bat test
 *
 *   4) Изучите дерево зависимостей:
 *        .\gradlew.bat dependencies
 *        .\gradlew.bat dependencies --configuration testRuntimeClasspath
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   .\gradlew.bat test → BUILD SUCCESSFUL, 1 test completed.
 *   dependencies — вывод вида:
 *     testRuntimeClasspath
 *     +--- org.junit:junit-bom:5.10.0 (...)
 *     |    \--- ...
 *     +--- org.junit.jupiter:junit-jupiter:5.10.0
 *          \--- org.junit.jupiter:junit-jupiter-api:5.10.0
 *               ...
 *
 * ПРОВЕРКА:
 *   В build/reports/tests/test/index.html откройте отчёт браузером.
 *
 * ПОДСКАЗКА:
 *   platform() указывает Gradle использовать BOM (Bill of Materials) —
 *   специальный POM без кода, только с <dependencyManagement>.
 *   Благодаря BOM не нужно указывать версии каждого junit-модуля отдельно.
 */
public class Task03 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
        System.out.println("Done");
    }
}
