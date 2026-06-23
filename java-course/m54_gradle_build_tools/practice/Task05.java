package m54_gradle_build_tools.practice;

/**
 * Задача 05 — Модуль 54: Build Lifecycle и кастомные задачи (tasks)
 *
 * ЗАДАНИЕ:
 *   Изучите порядок выполнения задач в Gradle и напишите кастомные tasks:
 *
 *   1) Возьмите проект hello-gradle из Task02. В build.gradle добавьте:
 *
 *        // Простая кастомная задача
 *        tasks.register('hello') {
 *            description = 'Приветственная задача'
 *            group       = 'custom'
 *            doLast {
 *                println "Привет от Gradle! Проект: ${project.name} v${project.version}"
 *            }
 *        }
 *
 *        // Задача с зависимостью от другой задачи
 *        tasks.register('buildInfo') {
 *            description = 'Показывает информацию о сборке'
 *            group       = 'custom'
 *            dependsOn 'classes'   // выполнится после компиляции
 *            doLast {
 *                println "Скомпилировано в: ${buildDir}/classes"
 *                println "Версия Java: ${JavaVersion.current()}"
 *            }
 *        }
 *
 *        // Задача-копировщик ресурсов
 *        tasks.register('copyReadme', Copy) {
 *            description = 'Копирует README в папку сборки'
 *            group       = 'custom'
 *            from '.'
 *            include 'README.md'
 *            into "${buildDir}/docs"
 *        }
 *
 *   2) Создайте README.md в корне проекта (любой текст).
 *
 *   3) Запустите задачи и изучите порядок выполнения:
 *        .\gradlew.bat hello
 *        .\gradlew.bat buildInfo
 *        .\gradlew.bat copyReadme
 *
 *   4) Изучите фазы build lifecycle явно:
 *        .\gradlew.bat compileJava
 *        .\gradlew.bat jar
 *        .\gradlew.bat test
 *        .\gradlew.bat check
 *        .\gradlew.bat build
 *      После каждой команды смотрите, какие задачи выполнились (лог в терминале).
 *
 *   5) Убедитесь в инкрементальности:
 *      Запустите .\gradlew.bat build дважды.
 *      Во второй раз все задачи должны быть UP-TO-DATE.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   hello → Привет от Gradle! Проект: hello-gradle v1.0.0
 *   buildInfo → показывает путь к классам и версию Java
 *   copyReadme → README.md скопирован в build/docs/
 *   Повторный build: > Task :compileJava UP-TO-DATE (и т.д.)
 *
 * ПОДСКАЗКА:
 *   Три фазы Gradle:
 *     1. Инициализация — читает settings.gradle, определяет проекты.
 *     2. Конфигурация — выполняет build.gradle, строит граф задач (DAG).
 *     3. Выполнение — запускает только нужные задачи в правильном порядке.
 *   Код внутри register { } (вне doLast/doFirst) — это фаза КОНФИГУРАЦИИ.
 *   Код внутри doLast { } — это фаза ВЫПОЛНЕНИЯ.
 */
public class Task05 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
        System.out.println("Done");
    }
}
