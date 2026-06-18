package m32_maven_multimodule.practice;

/**
 * Задача 07 — Модуль 32 (МИНИ-ПРОЕКТ): Полноценный многомодульный проект
 *
 * ЗАДАНИЕ:
 *   Соберите многомодульное приложение «с нуля», объединив все приёмы
 *   модуля. Структура:
 *
 *     task-tracker/                (parent, packaging=pom)
 *       ├── model/                 (классы данных: Task, Status)
 *       ├── service/               (бизнес-логика; зависит от model)
 *       └── cli/                   (точка входа main; зависит от service)
 *
 *   Требования:
 *     1) родительский POM с <modules> и общими свойствами (Java 17, UTF-8);
 *     2) <dependencyManagement> в родителе (например, для junit-jupiter);
 *     3) межмодульные зависимости (service → model, cli → service);
 *     4) профили dev/prod (разные настройки, например имя файла-хранилища);
 *     5) в model — тесты JUnit (scope test, версия из dependencyManagement);
 *     6) в cli — maven-shade-plugin для исполняемого jar;
 *     7) сборка из корня: mvn clean install; запуск: java -jar cli/target/cli-*.jar.
 *
 * ПРОВЕРКА:
 *   - mvn clean install в корне → BUILD SUCCESS, реактор собирает
 *     model → service → cli;
 *   - тесты model проходят;
 *   - исполняемый jar cli запускается и использует service и model;
 *   - mvn package -P prod подхватывает prod-настройки.
 *
 * ПОДСКАЗКА:
 *   Делайте по шагам и после каждого запускайте mvn и читайте лог
 *   реактора (порядок сборки модулей). Это репетиция реального проекта.
 */
public class Task07 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
        System.out.println("Done");
    }
}
