package m112_docker_dockerfile.practice;

/**
 * Задача 03 — Модуль 112: порядок инструкций и кэш слоёв
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите Dockerfile, который собирает проект ВНУТРИ, но эффективно кэширует зависимости.
 *   Ключ: стабильное (манифесты сборки) — раньше, изменчивое (код) — позже.
 *   1) В Dockerfile:
 *        FROM eclipse-temurin:17-jdk
 *        WORKDIR /src
 *        COPY gradlew settings.gradle build.gradle ./
 *        COPY gradle ./gradle
 *        RUN ./gradlew dependencies --no-daemon       # слой кэшируется, пока build.gradle не менялся
 *        COPY src ./src                                # код — отдельным слоем НИЖЕ
 *        RUN ./gradlew bootJar --no-daemon
 *        ENTRYPOINT ["java", "-jar", "build/libs/app.jar"]
 *   2) Соберите дважды: первый раз — долго; измените код, пересоберите — зависимости из кэша.
 *   3) Впишите Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: понять, что порядок инструкций определяет, какие слои переиспользуются из кэша.
 *
 * ПРИНЦИП:
 *   изменение файла инвалидирует ЕГО слой и ВСЕ слои ПОСЛЕ; слои ДО берутся из кэша.
 *   → стабильное наверх, изменчивое вниз.
 *
 * ПОДСКАЗКА: COPY кода ПОСЛЕ загрузки зависимостей — тогда правка кода не перекачивает зависимости.
 */
public class Task03 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: Dockerfile с порядком слоёв под кэш
                # FROM eclipse-temurin:17-jdk
                # WORKDIR /src
                # COPY gradlew settings.gradle build.gradle ./
                # COPY gradle ./gradle
                # RUN ./gradlew dependencies --no-daemon     # кэш зависимостей
                # COPY src ./src                              # код — ниже
                # RUN ./gradlew bootJar --no-daemon
                # ENTRYPOINT ["java", "-jar", "build/libs/app.jar"]
                """;
        System.out.println(dockerfile);
    }
}
