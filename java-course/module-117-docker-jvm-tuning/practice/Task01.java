/**
 * Задача 01 — Модуль 117: лимиты памяти и MaxRAMPercentage
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Запустите приложение с лимитом памяти и heap в процентах (вместо фиксированного -Xmx):
 *   1) Лимит контейнера 512 МБ, heap = 75% от него:
 *        docker run -m 512m -e JAVA_OPTS="-XX:MaxRAMPercentage=75.0" \
 *            -p 8080:8080 tasktracker:1.0.0
 *   2) Проверьте, что JVM видит лимит:
 *        docker run -m 512m eclipse-temurin:17-jre \
 *            java -XX:+PrintFlagsFinal -version | grep MaxHeapSize
 *        (MaxHeapSize должен быть ~75% от 512 МБ, а не от памяти хоста).
 *   3) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: усвоить, что heap задают процентом от лимита контейнера, а не фиксированным -Xmx.
 *
 * ВАЖНО: оставляйте ~25% запаса — metaspace, стеки потоков, off-heap память не входят в heap.
 *        Heap больше лимита → контейнер убивается (OOM, код выхода 137).
 *
 * ПОДСКАЗКА: JDK 11/17 container-aware — видит cgroup-лимит -m автоматически.
 */
public class Task01 {
    public static void main(String[] args) {
        String commands = """
                # TODO: запуск с лимитом памяти и MaxRAMPercentage
                # docker run -m 512m -e JAVA_OPTS="-XX:MaxRAMPercentage=75.0" \\
                #     -p 8080:8080 tasktracker:1.0.0
                #
                # Проверка, что JVM видит лимит контейнера:
                # docker run -m 512m eclipse-temurin:17-jre \\
                #     java -XX:+PrintFlagsFinal -version | grep MaxHeapSize
                """;
        System.out.println(commands);
    }
}
