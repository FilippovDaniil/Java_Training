/**
 * Задача 06 — Модуль 118: альтернатива через Buildpacks (минимум артефактов)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Оформите тот же production-стек, но БЕЗ Dockerfile — образ собирают Buildpacks (модуль 113):
 *   1) build.gradle:
 *        tasks.named('bootBuildImage') {
 *            imageName = 'tasktracker:1.0.0'
 *            environment = ['BP_JVM_VERSION': '17']
 *        }
 *   2) Сборка образа: ./gradlew bootBuildImage   (non-root, layers, JVM-tuning — из коробки).
 *   3) docker-compose.yml ссылается на готовый образ (image вместо build):
 *        services:
 *          app:
 *            image: tasktracker:1.0.0
 *            depends_on: { db: { condition: service_healthy } }
 *            environment: { ... как в задаче 02 ... }
 *            ports: ["8080:8080"]
 *   4) Впишите фрагменты в SOLUTION ниже.
 *
 * ЦЕЛЬ: показать, что для стандартного приложения Buildpacks убирают необходимость в Dockerfile.
 *
 * ВАЖНО: Buildpacks дают non-root, layered jars и контейнер-aware JVM по умолчанию — меньше ручной работы.
 *
 * ПОДСКАЗКА: в Compose тогда указывают image: (готовый образ), а не build: (сборка из Dockerfile).
 */
public class Task06 {
    public static void main(String[] args) {
        String solution = """
                # TODO: вариант через Buildpacks (без Dockerfile)
                # build.gradle:
                #   tasks.named('bootBuildImage') {
                #       imageName = 'tasktracker:1.0.0'
                #       environment = ['BP_JVM_VERSION': '17']
                #   }
                # ./gradlew bootBuildImage
                #
                # docker-compose.yml (app использует готовый образ):
                #   services:
                #     app:
                #       image: tasktracker:1.0.0
                #       depends_on: { db: { condition: service_healthy } }
                #       environment: { SPRING_PROFILES_ACTIVE: prod, ... }
                #       ports: ["8080:8080"]
                """;
        System.out.println(solution);
    }
}
