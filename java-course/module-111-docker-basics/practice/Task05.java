/**
 * Задача 05 — Модуль 111: запуск Java-приложения в контейнере вручную
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Запустите готовый jar в официальном образе JRE, БЕЗ написания Dockerfile (это будет в модуле 112):
 *   1) Соберите jar вашего Spring Boot приложения: ./gradlew bootJar
 *        (получится build/libs/app.jar — имя зависит от проекта).
 *   2) Запустите его в контейнере с пробросом порта и монтированием jar:
 *        docker run --rm -p 8080:8080 -v "$(pwd)/build/libs/app.jar:/app.jar" \
 *            eclipse-temurin:17-jre java -jar /app.jar
 *      (Windows PowerShell: -v "${PWD}/build/libs/app.jar:/app.jar")
 *   3) Проверьте: curl http://localhost:8080/actuator/health (или ваш эндпоинт).
 *   4) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: увидеть, что приложение работает в контейнере с официальным JRE-образом.
 *
 * ВАЖНО: --rm удалит контейнер после остановки; -v монтирует jar внутрь (пока без своего образа).
 *
 * ПОДСКАЗКА: это «ручной» способ — в модуле 112 jar «запечём» в собственный образ через Dockerfile.
 */
public class Task05 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды запуска jar в контейнере
                # ./gradlew bootJar
                # docker run --rm -p 8080:8080 \\
                #     -v "$(pwd)/build/libs/app.jar:/app.jar" \\
                #     eclipse-temurin:17-jre java -jar /app.jar
                # curl http://localhost:8080/actuator/health
                """;
        System.out.println(commands);
    }
}
