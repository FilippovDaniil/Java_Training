package m117_docker_jvm_tuning.practice;

/**
 * Задача 07 — Модуль 117: МИНИ-ПРОЕКТ «Production-hardened образ Task Tracker»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, bare-javac).
 *
 * ЦЕЛЬ: собрать Dockerfile, объединяющий весь production-tuning модуля:
 *       минимальная база, non-root, JVM под лимиты, HEALTHCHECK. Капстоун модуля.
 *
 * ЗАДАНИЕ — напишите Dockerfile и поместите его в DOCKERFILE ниже:
 *   FROM eclipse-temurin:17-jre-alpine
 *   RUN addgroup -S app && adduser -S app -G app
 *   WORKDIR /app
 *   COPY --chown=app:app build/libs/*.jar app.jar
 *   USER app
 *   ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
 *   EXPOSE 8080
 *   HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
 *       CMD wget -qO- http://localhost:8080/actuator/health || exit 1
 *   ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
 *
 *   Запуск с лимитами:
 *     docker run -d -m 512m --cpus="2" -p 8080:8080 tasktracker:1.0.0
 *
 *   Чек-лист hardening:
 *     [ ] JRE-alpine (не JDK)         [ ] USER app (non-root)
 *     [ ] MaxRAMPercentage (не -Xmx)  [ ] HEALTHCHECK (actuator)
 *     [ ] лимиты -m/--cpus при run    [ ] просканирован docker scout
 *
 * ОЖИДАЕМЫЙ ИТОГ: безопасный, компактный, контейнер-aware образ. Войдёт в финальный
 *   шаблон (модуль 118) вместе с Compose-стеком.
 *
 * ПОДСКАЗКА: соедините JAVA_OPTS (задача 02), non-root (05), HEALTHCHECK (06), лимиты (01/03).
 */
public class Task07 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: production-hardened Dockerfile
                # FROM eclipse-temurin:17-jre-alpine
                # RUN addgroup -S app && adduser -S app -G app
                # WORKDIR /app
                # COPY --chown=app:app build/libs/*.jar app.jar
                # USER app
                # ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
                # EXPOSE 8080
                # HEALTHCHECK --interval=30s --timeout=3s --retries=3 \\
                #     CMD wget -qO- http://localhost:8080/actuator/health || exit 1
                # ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
                #
                # docker run -d -m 512m --cpus="2" -p 8080:8080 tasktracker:1.0.0
                #
                # Чек-лист: JRE-alpine / non-root / MaxRAMPercentage / HEALTHCHECK / лимиты / scout
                """;
        System.out.println(dockerfile);
    }
}
