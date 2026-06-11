package m112_docker_dockerfile.practice;

/**
 * Задача 06 — Модуль 112: «собрать снаружи + COPY» (надёжная упаковка)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Альтернатива multi-stage: jar собирается НА ХОСТЕ, Dockerfile только копирует.
 *   Это надёжнее в средах с нестабильным интернетом (Gradle wrapper не качает дистрибутив в Docker).
 *   1) Соберите jar на хосте: ./gradlew bootJar
 *   2) Минимальный Dockerfile (только рантайм):
 *        FROM eclipse-temurin:17-jre-alpine
 *        WORKDIR /app
 *        COPY build/libs/*.jar app.jar
 *        EXPOSE 8080
 *        ENTRYPOINT ["java", "-jar", "app.jar"]
 *   3) .dockerignore — исключить build, НО пропустить jar:
 *        build/
 *        !build/libs/*.jar
 *   4) Соберите: docker build --provenance=false -t tasktracker:1.0.0 .
 *   5) Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: освоить подход «сборка на хосте → Docker только COPY» (быстро и предсказуемо).
 *
 * ВАЖНО (из практики, ~/.claude/CLAUDE.md): multi-stage с Gradle ненадёжна (timeout загрузки
 *   дистрибутива). Для локальной упаковки надёжнее собрать jar на хосте и COPY его в образ.
 *
 * ПОДСКАЗКА: jre-alpine даёт особенно компактный образ; --provenance=false — для совместимости.
 */
public class Task06 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile «только COPY» (jar собран на хосте)
                # FROM eclipse-temurin:17-jre-alpine
                # WORKDIR /app
                # COPY build/libs/*.jar app.jar
                # EXPOSE 8080
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                #
                # .dockerignore:
                #   build/
                #   !build/libs/*.jar
                #
                # ./gradlew bootJar
                # docker build --provenance=false -t tasktracker:1.0.0 .
                """;
        System.out.println(dockerfile);
    }
}
