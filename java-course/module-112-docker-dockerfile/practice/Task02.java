/**
 * Задача 02 — Модуль 112: ENTRYPOINT vs CMD (фиксированная команда + аргументы по умолчанию)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сделайте профиль запуска переопределяемым: команда фиксирована (ENTRYPOINT),
 *   аргументы по умолчанию — в CMD.
 *   1) В Dockerfile:
 *        FROM eclipse-temurin:17-jre
 *        WORKDIR /app
 *        COPY build/libs/app.jar app.jar
 *        ENTRYPOINT ["java", "-jar", "app.jar"]
 *        CMD ["--spring.profiles.active=prod"]
 *   2) Соберите: docker build -t tasktracker:1.0.0 .
 *   3) Запуск по умолчанию (профиль prod):  docker run tasktracker:1.0.0
 *   4) Переопределение профиля при запуске:  docker run tasktracker:1.0.0 --spring.profiles.active=dev
 *   5) Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: понять связку ENTRYPOINT (неизменная команда) + CMD (переопределяемые аргументы).
 *
 * ТАБЛИЦА:
 *   ENTRYPOINT — НЕ переопределяется аргументами docker run (только --entrypoint);
 *   CMD        — переопределяется аргументами после имени образа.
 *
 * ПОДСКАЗКА: аргументы после имени образа заменяют CMD, но не ENTRYPOINT.
 */
public class Task02 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile с ENTRYPOINT + CMD
                # FROM eclipse-temurin:17-jre
                # WORKDIR /app
                # COPY build/libs/app.jar app.jar
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                # CMD ["--spring.profiles.active=prod"]
                #
                # docker run tasktracker:1.0.0                                  # профиль prod (CMD)
                # docker run tasktracker:1.0.0 --spring.profiles.active=dev     # переопределили CMD
                """;
        System.out.println(dockerfile);
    }
}
