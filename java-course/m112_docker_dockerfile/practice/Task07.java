package m112_docker_dockerfile.practice;

/**
 * Задача 07 — Модуль 112: МИНИ-ПРОЕКТ «Production-ready Dockerfile для Task Tracker»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЦЕЛЬ: собрать оптимальный образ Spring Boot, объединив всё из модуля:
 *       multi-stage, кэш зависимостей, компактный JRE, документированный порт.
 *
 * ЗАДАНИЕ — напишите Dockerfile и поместите его в DOCKERFILE ниже:
 *   # ── этап сборки: кэшируем зависимости отдельно от кода ──
 *   FROM eclipse-temurin:17-jdk AS build
 *   WORKDIR /src
 *   COPY gradlew settings.gradle build.gradle ./
 *   COPY gradle ./gradle
 *   RUN ./gradlew dependencies --no-daemon          # слой зависимостей (кэшируется)
 *   COPY src ./src
 *   RUN ./gradlew bootJar --no-daemon
 *
 *   # ── этап рантайма: только JRE + jar ──
 *   FROM eclipse-temurin:17-jre-alpine
 *   WORKDIR /app
 *   COPY --from=build /src/build/libs/*.jar app.jar
 *   EXPOSE 8080
 *   ENTRYPOINT ["java", "-jar", "app.jar"]
 *   CMD ["--spring.profiles.active=prod"]
 *
 *   Плюс .dockerignore: .git, .idea, .gradle, build, *.md
 *
 *   Сборка: docker build -t tasktracker:1.0.0 .
 *   Запуск: docker run -p 8080:8080 tasktracker:1.0.0
 *
 * ОЖИДАЕМЫЙ ИТОГ: компактный, быстро пересобираемый образ Spring Boot — основа для модуля 113
 *   (layered jars / Buildpacks) и Compose (модуль 115).
 *
 * ПОДСКАЗКА: соедините multi-stage (задача 05), кэш слоёв (03), ENTRYPOINT+CMD (02), .dockerignore (04).
 */
public class Task07 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: production-ready Dockerfile (multi-stage + кэш + JRE-alpine)
                # ── этап сборки ──
                # FROM eclipse-temurin:17-jdk AS build
                # WORKDIR /src
                # COPY gradlew settings.gradle build.gradle ./
                # COPY gradle ./gradle
                # RUN ./gradlew dependencies --no-daemon
                # COPY src ./src
                # RUN ./gradlew bootJar --no-daemon
                # ── этап рантайма ──
                # FROM eclipse-temurin:17-jre-alpine
                # WORKDIR /app
                # COPY --from=build /src/build/libs/*.jar app.jar
                # EXPOSE 8080
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                # CMD ["--spring.profiles.active=prod"]
                #
                # docker build -t tasktracker:1.0.0 .
                # docker run -p 8080:8080 tasktracker:1.0.0
                """;
        System.out.println(dockerfile);
    }
}
