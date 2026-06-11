package m118_docker_final_template.practice;

/**
 * Задача 01 — Модуль 118: финальный Dockerfile (multi-stage + layered + non-root + tuning)
 *
 * ФОРМАТ: носитель артефакта. .java с text-блоком (Dockerfile) и println — компилируется bare-javac.
 *
 * ЗАДАНИЕ:
 *   Соберите итоговый production-ready Dockerfile, объединив всё из модулей 112/113/117:
 *     # ── сборка ──
 *     FROM eclipse-temurin:17-jdk AS build
 *     WORKDIR /src
 *     COPY gradlew settings.gradle build.gradle ./
 *     COPY gradle ./gradle
 *     RUN ./gradlew dependencies --no-daemon
 *     COPY src ./src
 *     RUN ./gradlew bootJar --no-daemon
 *     # ── рантайм ──
 *     FROM eclipse-temurin:17-jre-alpine
 *     RUN addgroup -S app && adduser -S app -G app
 *     WORKDIR /app
 *     COPY --from=build --chown=app:app /src/build/libs/*.jar app.jar
 *     USER app
 *     ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
 *     EXPOSE 8080
 *     HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
 *         CMD wget -qO- http://localhost:8080/actuator/health || exit 1
 *     ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
 *   Соберите: docker build -t tasktracker:1.0.0 . ; впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: получить эталонный Dockerfile, совмещающий все best practices курса.
 *
 * ВАЖНО: компилятор/Gradle остаются в этапе build; в рантайм идёт только jar (компактно и безопасно).
 *
 * ПОДСКАЗКА: это сумма задач — multi-stage (112), кэш слоёв (112), non-root + tuning + healthcheck (117).
 */
public class Task01 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: финальный production-ready Dockerfile
                # ── сборка ──
                # FROM eclipse-temurin:17-jdk AS build
                # WORKDIR /src
                # COPY gradlew settings.gradle build.gradle ./
                # COPY gradle ./gradle
                # RUN ./gradlew dependencies --no-daemon
                # COPY src ./src
                # RUN ./gradlew bootJar --no-daemon
                # ── рантайм ──
                # FROM eclipse-temurin:17-jre-alpine
                # RUN addgroup -S app && adduser -S app -G app
                # WORKDIR /app
                # COPY --from=build --chown=app:app /src/build/libs/*.jar app.jar
                # USER app
                # ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
                # EXPOSE 8080
                # HEALTHCHECK --interval=30s --timeout=3s --retries=3 \\
                #     CMD wget -qO- http://localhost:8080/actuator/health || exit 1
                # ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
                """;
        System.out.println(dockerfile);
    }
}
