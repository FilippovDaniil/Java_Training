/**
 * Задача 01 — Модуль 54: Gradle Wrapper и структура проекта
 *
 * Задачи модуля выполняются командами Gradle и редактированием build.gradle.
 *
 * ЗАДАНИЕ:
 *   Инициализируйте новый Gradle-проект и изучите его структуру:
 *
 *   1) Создайте папку my-gradle-app и перейдите в неё.
 *
 *   2) Если Gradle установлен глобально — инициализируйте проект:
 *        gradle init --type java-application --dsl groovy
 *      Если Gradle НЕ установлен — создайте структуру вручную:
 *        mkdir -p src/main/java  src/test/java
 *        gradle wrapper --gradle-version 8.8
 *        (предварительно установите Gradle любым способом)
 *
 *   3) Изучите созданные файлы:
 *        gradlew / gradlew.bat   — скрипты Gradle Wrapper
 *        gradle/wrapper/gradle-wrapper.properties — версия и URL дистрибутива
 *        settings.gradle         — имя проекта
 *        build.gradle            — скрипт сборки
 *        src/main/java/          — исходники
 *
 *   4) Выполните команды через wrapper и сохраните вывод:
 *        .\gradlew.bat --version        (Windows)
 *        ./gradlew --version            (Unix/Mac)
 *
 *        .\gradlew.bat tasks            — список всех задач
 *        .\gradlew.bat tasks --all      — расширенный список
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   --version выведет строку вида:
 *     Gradle 8.8 / Build time: 2024-05-31
 *   tasks выведет группы задач: Build tasks, Verification tasks, Help tasks, ...
 *
 * ПОДСКАЗКА:
 *   gradle-wrapper.properties хранит ссылку distributionUrl — именно отсюда
 *   wrapper скачивает нужную версию Gradle (~/.gradle/wrapper/dists/).
 *   Один раз скачал — всегда использует кэш, интернет не нужен.
 */
public class Task01 {
    public static void main(String[] args) {
        System.out.println("Задание выполняется в терминале — см. JavaDoc-комментарий.");
    }
}
