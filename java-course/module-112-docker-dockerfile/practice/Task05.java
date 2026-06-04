/**
 * Задача 05 — Модуль 112: multi-stage build (сборка и рантайм раздельно)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите компактный образ через два этапа: сборка в JDK+Gradle, рантайм — только JRE+jar.
 *   1) В Dockerfile:
 *        # ── этап сборки ──
 *        FROM eclipse-temurin:17-jdk AS build
 *        WORKDIR /src
 *        COPY . .
 *        RUN ./gradlew bootJar --no-daemon
 *        # ── этап рантайма ──
 *        FROM eclipse-temurin:17-jre
 *        WORKDIR /app
 *        COPY --from=build /src/build/libs/*.jar app.jar
 *        EXPOSE 8080
 *        ENTRYPOINT ["java", "-jar", "app.jar"]
 *   2) Соберите: docker build -t tasktracker:1.0.0 .
 *   3) Сравните размер с однослойной сборкой: docker images tasktracker
 *   4) Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: получить маленький рантайм-образ — компилятор и кэш Gradle НЕ попадают в финал.
 *
 * КЛЮЧ: COPY --from=build переносит ТОЛЬКО jar из этапа сборки в чистый рантайм-образ.
 *
 * ПОДСКАЗКА: финальный образ = последний FROM; всё из предыдущих этапов отбрасывается, кроме скопированного.
 */
public class Task05 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: multi-stage Dockerfile
                # ── этап сборки ──
                # FROM eclipse-temurin:17-jdk AS build
                # WORKDIR /src
                # COPY . .
                # RUN ./gradlew bootJar --no-daemon
                # ── этап рантайма ──
                # FROM eclipse-temurin:17-jre
                # WORKDIR /app
                # COPY --from=build /src/build/libs/*.jar app.jar
                # EXPOSE 8080
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                """;
        System.out.println(dockerfile);
    }
}
