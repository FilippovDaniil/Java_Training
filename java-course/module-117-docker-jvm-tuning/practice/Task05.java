/**
 * Задача 05 — Модуль 117: non-root пользователь в Dockerfile
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Запустите приложение от непривилегированного пользователя (не root):
 *     FROM eclipse-temurin:17-jre-alpine
 *     RUN addgroup -S app && adduser -S app -G app      # создать группу и пользователя app
 *     WORKDIR /app
 *     COPY --chown=app:app build/libs/*.jar app.jar     # файл принадлежит app
 *     USER app                                           # переключиться на app
 *     EXPOSE 8080
 *     ENTRYPOINT ["java", "-jar", "app.jar"]
 *   Проверьте, от кого работает процесс:
 *     docker run --rm tasktracker:1.0.0 id        # uid/gid НЕ 0 (не root)
 *   Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: повысить безопасность — пробой приложения не даёт root-доступа к контейнеру.
 *
 * ВАЖНО: USER ставят ПОСЛЕ установки пакетов/копирования (root для них), но ДО ENTRYPOINT.
 *        --chown гарантирует, что non-root пользователь имеет права на jar.
 *
 * ПОДСКАЗКА: addgroup/adduser -S — синтаксис alpine; Buildpacks делают non-root из коробки.
 */
public class Task05 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile с non-root пользователем
                # FROM eclipse-temurin:17-jre-alpine
                # RUN addgroup -S app && adduser -S app -G app
                # WORKDIR /app
                # COPY --chown=app:app build/libs/*.jar app.jar
                # USER app
                # EXPOSE 8080
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                #
                # docker run --rm tasktracker:1.0.0 id     # uid/gid != 0
                """;
        System.out.println(dockerfile);
    }
}
