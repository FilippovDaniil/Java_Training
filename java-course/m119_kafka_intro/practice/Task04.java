package m119_kafka_intro.practice;

/**
 * Задача 04 — Модуль 119: ключ сообщения -> партиция -> порядок
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Покажите на практике, что ключ определяет партицию, а порядок гарантирован только внутри неё.
 *   1) Отправьте сообщения С КЛЮЧОМ (ключ и значение разделяет ':'):
 *        docker compose exec -it kafka /opt/kafka/bin/kafka-console-producer.sh \
 *            --bootstrap-server localhost:9092 --topic task.created \
 *            --property parse.key=true --property key.separator=:
 *        > task-42:{"id":42,"status":"NEW"}
 *        > task-42:{"id":42,"status":"IN_PROGRESS"}
 *        > task-42:{"id":42,"status":"DONE"}
 *        > task-7:{"id":7,"status":"NEW"}
 *   2) Прочитайте с раскрытием метаданных:
 *        docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \
 *            --bootstrap-server localhost:9092 --topic task.created --from-beginning \
 *            --property print.key=true --property print.partition=true --property print.offset=true
 *   3) Ответьте в COMMANDS:
 *        - в какие партиции ушли сообщения с ключом task-42? (все в одну?)
 *        - в какой партиции оказался task-7?
 *        - что будет с порядком статусов task-42, если отправлять их БЕЗ ключа?
 *
 * ОЖИДАЕМЫЙ ИТОГ: все три записи task-42 лежат в одной партиции с возрастающими офсетами;
 *   task-7 может попасть в другую партицию. Порядок NEW -> IN_PROGRESS -> DONE сохранён.
 *
 * ЦЕЛЬ: понять главный приём проектирования событий: ключ = идентификатор сущности.
 *
 * ВАЖНО: без ключа записи распределяются по партициям, и «DONE» может быть обработан
 *   раньше «NEW» разными консьюмерами группы.
 *
 * ПОДСКАЗКА: партиция = hash(key) % partitionCount, поэтому один ключ -> всегда одна партиция
 *   (пока число партиций не меняли).
 */
public class Task04 {
    public static void main(String[] args) {
        String commands = """
                # TODO: сообщения с ключом и разбор партиций
                # docker compose exec -it kafka /opt/kafka/bin/kafka-console-producer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created \\
                #     --property parse.key=true --property key.separator=:
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created --from-beginning \\
                #     --property print.key=true --property print.partition=true --property print.offset=true
                #
                # ОТВЕТЫ:
                # task-42 -> партиция ... (офсеты ...)
                # task-7  -> партиция ...
                # без ключа порядок -> ...
                """;
        System.out.println(commands);
    }
}
