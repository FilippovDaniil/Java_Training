/**
 * Задача 07 — Модуль 27 (МИНИ-ПРОЕКТ): Maven-проект «с нуля до jar»
 *
 * ЗАДАНИЕ:
 *   Создайте полноценный Maven-проект и пройдите весь путь до рабочего
 *   артефакта, объединив все навыки модуля.
 *
 *   1. Структура проекта json-greeter (src/main/java, src/test/java).
 *   2. pom.xml:
 *        - координаты (com.example : json-greeter : 1.0.0);
 *        - Java 17, UTF-8;
 *        - зависимость gson (compile);
 *        - зависимость junit-jupiter (scope test);
 *        - maven-compiler-plugin.
 *   3. Код в src/main/java: класс Greeter, который через Gson
 *      сериализует объект приветствия в JSON, и Main, печатающий JSON.
 *   4. Тест в src/test/java (любой простой JUnit-тест — см. модуль 28).
 *   5. Соберите и проверьте:
 *        mvn clean test       — тесты проходят;
 *        mvn package          — собран jar;
 *        запустите программу и убедитесь, что выводится JSON.
 *
 * ПРОВЕРКА (что вы должны получить):
 *   - mvn dependency:tree показывает gson и junit;
 *   - mvn test → BUILD SUCCESS, тест зелёный;
 *   - в target/ лежит json-greeter-1.0.0.jar;
 *   - программа печатает корректный JSON.
 *
 * ПОДСКАЗКА:
 *   Это закрепление всего модуля: структура + pom + зависимости +
 *   scope + плагин + жизненный цикл. Делайте по шагам, после каждого
 *   запускайте mvn и читайте вывод.
 */
public class Task07 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
