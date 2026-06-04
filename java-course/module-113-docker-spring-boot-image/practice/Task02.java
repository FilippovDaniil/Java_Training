/**
 * Задача 02 — Модуль 113: Dockerfile с layered jars
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите образ, где зависимости и код — РАЗНЫЕ слои (правка кода не перекачивает зависимости).
 *   1) В Dockerfile:
 *        # этап извлечения слоёв
 *        FROM eclipse-temurin:17-jre AS builder
 *        WORKDIR /app
 *        COPY build/libs/app.jar app.jar
 *        RUN java -Djarmode=layertools -jar app.jar extract
 *        # рантайм: копируем слои по порядку (стабильное → изменчивое)
 *        FROM eclipse-temurin:17-jre
 *        WORKDIR /app
 *        COPY --from=builder /app/dependencies/ ./
 *        COPY --from=builder /app/spring-boot-loader/ ./
 *        COPY --from=builder /app/snapshot-dependencies/ ./
 *        COPY --from=builder /app/application/ ./
 *        ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
 *   2) Соберите: docker build -t tasktracker:1.0.0 .
 *   3) Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: расслоить образ — слой application (ваш код) тонкий и пересобирается отдельно от зависимостей.
 *
 * ВАЖНО: порядок COPY слоёв — от стабильного (dependencies) к изменчивому (application); код последним.
 *
 * ПОДСКАЗКА: запуск распакованного приложения — через JarLauncher, не через java -jar.
 */
public class Task02 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile с layered jars
                # FROM eclipse-temurin:17-jre AS builder
                # WORKDIR /app
                # COPY build/libs/app.jar app.jar
                # RUN java -Djarmode=layertools -jar app.jar extract
                #
                # FROM eclipse-temurin:17-jre
                # WORKDIR /app
                # COPY --from=builder /app/dependencies/ ./
                # COPY --from=builder /app/spring-boot-loader/ ./
                # COPY --from=builder /app/snapshot-dependencies/ ./
                # COPY --from=builder /app/application/ ./
                # ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
                """;
        System.out.println(dockerfile);
    }
}
