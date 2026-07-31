package m119_kafka_intro.practice;

/**
 * Задача 02 — Модуль 119: топики через CLI (create, list, describe, alter partitions)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   На поднятом брокере (задача 01) поработайте с топиками. Префикс для краткости:
 *     K=/opt/kafka/bin ; все команды — через docker compose exec kafka $K/...
 *   1) Создать топик сквозного проекта с 3 партициями:
 *        kafka-topics.sh --bootstrap-server localhost:9092 \
 *            --create --topic task.created --partitions 3 --replication-factor 1
 *   2) Список топиков: --list  (найдите свой в выводе)
 *   3) Описать топик: --describe --topic task.created
 *        - разберите вывод: PartitionCount, ReplicationFactor, Leader, Replicas, Isr
 *   4) Увеличить число партиций до 6: --alter --topic task.created --partitions 6
 *   5) Убедиться, что стало 6: --describe (и попробовать уменьшить до 3 — увидеть ошибку)
 *   Впишите команды и свои наблюдения в COMMANDS.
 *
 * ОЖИДАЕМЫЙ ИТОГ: describe показывает 6 партиций; попытка уменьшить партиции завершается
 *   ошибкой «Topic currently has 6 partitions, which is higher than the requested 3».
 *
 * ЦЕЛЬ: научиться создавать и осматривать топик, понять, что партиции — «дорога в одну сторону».
 *
 * ВАЖНО: replication-factor не может быть больше числа брокеров (у нас 1 брокер -> RF=1).
 *
 * ПОДСКАЗКА: Isr = in-sync replicas (реплики, догнавшие лидера); при одном брокере Leader,
 *   Replicas и Isr будут указывать на node id 1.
 */
public class Task02 {
    public static void main(String[] args) {
        String commands = """
                # TODO: команды работы с топиками (K=/opt/kafka/bin)
                # docker compose exec kafka $K/kafka-topics.sh --bootstrap-server localhost:9092 \\
                #     --create --topic task.created --partitions 3 --replication-factor 1
                # docker compose exec kafka $K/kafka-topics.sh --bootstrap-server localhost:9092 --list
                # docker compose exec kafka $K/kafka-topics.sh --bootstrap-server localhost:9092 \\
                #     --describe --topic task.created
                # docker compose exec kafka $K/kafka-topics.sh --bootstrap-server localhost:9092 \\
                #     --alter --topic task.created --partitions 6
                #
                # НАБЛЮДЕНИЯ (заполните):
                # PartitionCount = ...   ReplicationFactor = ...   Leader = ...   Isr = ...
                # попытка уменьшить партиции -> ...
                """;
        System.out.println(commands);
    }
}
