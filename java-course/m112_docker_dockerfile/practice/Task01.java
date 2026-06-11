package m112_docker_dockerfile.practice;

/**
 * Задача 01 — Модуль 112: первый Dockerfile (FROM, COPY, ENTRYPOINT)
 *
 * ФОРМАТ: носитель артефакта. .java с text-блоком (несёт Dockerfile) и println —
 *   компилируется bare-javac. Сам Dockerfile создайте в корне проекта.
 *
 * ЗАДАНИЕ:
 *   1) Соберите jar: ./gradlew bootJar (получится build/libs/app.jar).
 *   2) Создайте файл Dockerfile в корне проекта и напишите в нём:
 *        FROM eclipse-temurin:17-jre
 *        WORKDIR /app
 *        COPY build/libs/app.jar app.jar
 *        EXPOSE 8080
 *        ENTRYPOINT ["java", "-jar", "app.jar"]
 *   3) Соберите образ: docker build -t tasktracker:1.0.0 .
 *   4) Запустите: docker run -p 8080:8080 tasktracker:1.0.0
 *   5) Тот же Dockerfile впишите в DOCKERFILE ниже (задача напечатает его как образец).
 *
 * ЦЕЛЬ: собрать первый собственный образ из jar (FROM → COPY → ENTRYPOINT).
 *
 * ВАЖНО: EXPOSE только ДОКУМЕНТИРУЕТ порт; наружу его открывает -p при docker run.
 *
 * ПОДСКАЗКА: точка в конце docker build -t ... . — это контекст сборки (текущая директория).
 */
public class Task01 {
    public static void main(String[] args) {
        String dockerfile = """
                # Файл: Dockerfile (в корне проекта)
                # TODO: напишите инструкции образа
                # FROM eclipse-temurin:17-jre
                # WORKDIR /app
                # COPY build/libs/app.jar app.jar
                # EXPOSE 8080
                # ENTRYPOINT ["java", "-jar", "app.jar"]
                #
                # Сборка:  docker build -t tasktracker:1.0.0 .
                # Запуск:  docker run -p 8080:8080 tasktracker:1.0.0
                """;
        System.out.println(dockerfile);
    }
}
