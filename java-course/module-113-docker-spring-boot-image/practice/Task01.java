/**
 * Задача 01 — Модуль 113: исследуем слои Spring Boot jar (layertools list)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   1) Соберите jar: ./gradlew bootJar
 *   2) Посмотрите, на какие слои Spring Boot раскладывает jar:
 *        java -Djarmode=layertools -jar build/libs/app.jar list
 *      Должны увидеть: dependencies, spring-boot-loader, snapshot-dependencies, application.
 *   3) Извлеките слои в папки (посмотреть структуру):
 *        java -Djarmode=layertools -jar build/libs/app.jar extract --destination layers
 *        ls layers/
 *   4) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: понять, что executable jar Spring Boot уже расслоён по изменчивости (для кэша Docker).
 *
 * ТАБЛИЦА СЛОЁВ (от стабильного к изменчивому):
 *   dependencies → spring-boot-loader → snapshot-dependencies → application (ваш код)
 *
 * ПОДСКАЗКА: layered jars включены по умолчанию (Spring Boot 2.3+) — отдельно настраивать не нужно.
 */
public class Task01 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды исследования слоёв
                # ./gradlew bootJar
                # java -Djarmode=layertools -jar build/libs/app.jar list
                # java -Djarmode=layertools -jar build/libs/app.jar extract --destination layers
                # ls layers/
                #   dependencies/ spring-boot-loader/ snapshot-dependencies/ application/
                """;
        System.out.println(commands);
    }
}
