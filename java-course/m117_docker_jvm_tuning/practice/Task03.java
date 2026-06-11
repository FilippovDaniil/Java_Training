package m117_docker_jvm_tuning.practice;

/**
 * Задача 03 — Модуль 117: лимиты CPU и потоки JVM
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Ограничьте CPU контейнера и убедитесь, что JVM это учитывает:
 *   1) Запуск с лимитом CPU и памяти:
 *        docker run --cpus="2" -m 1g -p 8080:8080 tasktracker:1.0.0
 *   2) Проверьте, сколько процессоров видит JVM:
 *        docker run --cpus="2" eclipse-temurin:17-jre \
 *            java -XX:+PrintFlagsFinal -version | grep ActiveProcessorCount
 *        либо в приложении: Runtime.getRuntime().availableProcessors() → 2.
 *   3) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: понять, что JDK 17 настраивает пулы потоков (GC, ForkJoinPool) по CPU-лимиту контейнера.
 *
 * ВАЖНО: без CPU-лимита JVM видит все ядра хоста и создаёт слишком много потоков под жёстким лимитом.
 *
 * ПОДСКАЗКА: availableProcessors() в контейнере с --cpus="2" вернёт 2 (JDK 17 учитывает cgroups).
 */
public class Task03 {
    public static void main(String[] args) {
        String commands = """
                # TODO: лимиты CPU и проверка видимых процессоров
                # docker run --cpus="2" -m 1g -p 8080:8080 tasktracker:1.0.0
                #
                # docker run --cpus="2" eclipse-temurin:17-jre \\
                #     java -XX:+PrintFlagsFinal -version | grep ActiveProcessorCount
                #
                # В приложении: Runtime.getRuntime().availableProcessors()  → 2
                """;
        System.out.println(commands);
    }
}
