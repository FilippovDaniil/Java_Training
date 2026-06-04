/**
 * Задача 02 — Модуль 117: JAVA_OPTS в Dockerfile (гибкая передача флагов JVM)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сделайте флаги JVM настраиваемыми через переменную окружения:
 *     FROM eclipse-temurin:17-jre-alpine
 *     WORKDIR /app
 *     COPY build/libs/*.jar app.jar
 *     ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
 *     EXPOSE 8080
 *     ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
 *   Теперь флаги можно переопределить при запуске:
 *     docker run -e JAVA_OPTS="-XX:MaxRAMPercentage=50.0 -XX:+UseG1GC" tasktracker:1.0.0
 *   Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: вынести JVM-флаги в ENV JAVA_OPTS — настраиваются снаружи без пересборки образа.
 *
 * ВАЖНО: форма ["sh","-c","java $JAVA_OPTS -jar app.jar"] нужна, чтобы оболочка подставила $JAVA_OPTS
 *        (в exec-форме ["java","$JAVA_OPTS",...] переменная НЕ раскрывается).
 *
 * ПОДСКАЗКА: дефолт в ENV задаёт разумное значение; -e при run его переопределяет.
 */
public class Task02 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile с настраиваемыми JAVA_OPTS
                # FROM eclipse-temurin:17-jre-alpine
                # WORKDIR /app
                # COPY build/libs/*.jar app.jar
                # ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
                # EXPOSE 8080
                # ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
                #
                # docker run -e JAVA_OPTS="-XX:MaxRAMPercentage=50.0 -XX:+UseG1GC" tasktracker:1.0.0
                """;
        System.out.println(dockerfile);
    }
}
