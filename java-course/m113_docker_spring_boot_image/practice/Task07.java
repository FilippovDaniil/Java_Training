package m113_docker_spring_boot_image.practice;

/**
 * Задача 07 — Модуль 113: МИНИ-ПРОЕКТ «Оптимальный образ Task Tracker: выбор и обоснование»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЦЕЛЬ: выбрать и оформить оптимальный способ упаковки Task Tracker, обосновав решение.
 *
 * ЗАДАНИЕ — оформите в SOLUTION ниже:
 *
 *   А) Вариант Buildpacks (рекомендуемый для стандартного приложения):
 *        build.gradle:
 *          tasks.named('bootBuildImage') { imageName = 'tasktracker:1.0.0'; environment = ['BP_JVM_VERSION':'17'] }
 *        сборка: ./gradlew bootBuildImage
 *
 *   Б) Вариант Dockerfile + layered jars (когда нужна тонкая настройка):
 *        FROM eclipse-temurin:17-jre AS builder
 *        WORKDIR /app
 *        COPY build/libs/app.jar app.jar
 *        RUN java -Djarmode=layertools -jar app.jar extract
 *        FROM eclipse-temurin:17-jre
 *        WORKDIR /app
 *        COPY --from=builder /app/dependencies/ ./
 *        COPY --from=builder /app/spring-boot-loader/ ./
 *        COPY --from=builder /app/snapshot-dependencies/ ./
 *        COPY --from=builder /app/application/ ./
 *        ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
 *
 *   В) Обоснование (1–2 строки): почему выбран вариант для вашего случая
 *      (стандартное приложение → Buildpacks; особые требования к образу → Dockerfile+layers).
 *
 * ОЖИДАЕМЫЙ ИТОГ: осознанный выбор способа упаковки + готовый артефакт. Этот образ пойдёт
 *   в конфигурацию (модуль 114) и docker-compose (модуль 115).
 *
 * ПОДСКАЗКА: для большинства Spring Boot приложений bootBuildImage — кратчайший путь к хорошему образу.
 */
public class Task07 {
    public static void main(String[] args) {
        String solution = """
                # TODO: оформите выбранный способ упаковки и обоснование

                # А) Buildpacks (рекомендуется):
                #   tasks.named('bootBuildImage') { imageName = 'tasktracker:1.0.0'; environment = ['BP_JVM_VERSION':'17'] }
                #   ./gradlew bootBuildImage

                # Б) Dockerfile + layered jars (если нужна тонкая настройка):
                #   FROM eclipse-temurin:17-jre AS builder
                #   ... extract слоёв ...
                #   FROM eclipse-temurin:17-jre
                #   COPY --from=builder /app/dependencies/ ./   (и остальные слои)
                #   ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

                # В) Обоснование:
                #   стандартное приложение → Buildpacks (best practices из коробки);
                #   особые требования к образу → Dockerfile + layered jars.
                """;
        System.out.println(solution);
    }
}
