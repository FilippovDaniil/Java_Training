package m113_docker_spring_boot_image.practice;

/**
 * Задача 03 — Модуль 113: образ без Dockerfile (bootBuildImage / Buildpacks)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите образ Spring Boot ВООБЩЕ без Dockerfile — через Cloud Native Buildpacks:
 *   1) Убедитесь, что Docker-демон запущен (Buildpacks собирают через него).
 *   2) Соберите образ:
 *        ./gradlew bootBuildImage --imageName=tasktracker:1.0.0
 *   3) Проверьте, что образ появился: docker images tasktracker
 *   4) Запустите: docker run -p 8080:8080 tasktracker:1.0.0
 *   5) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: получить production-grade образ одной командой — Buildpacks сами подберут JRE,
 *       применят layered jars, настроят JVM и non-root пользователя.
 *
 * ВАЖНО: Dockerfile не нужен. Требуется доступный Docker-демон.
 *
 * ПОДСКАЗКА: имя образа можно задать флагом --imageName или в build.gradle (см. задачу 04).
 */
public class Task03 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды сборки образа через Buildpacks
                # ./gradlew bootBuildImage --imageName=tasktracker:1.0.0
                # docker images tasktracker
                # docker run -p 8080:8080 tasktracker:1.0.0
                """;
        System.out.println(commands);
    }
}
