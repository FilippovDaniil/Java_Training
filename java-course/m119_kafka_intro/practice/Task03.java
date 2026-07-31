package m119_kafka_intro.practice;

/**
 * Задача 03 — Модуль 119: console-producer и console-consumer, чтение с начала лога
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Убедитесь, что Kafka — это лог, который можно перечитывать.
 *   1) В первом терминале — консьюмер (оставьте работать):
 *        docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \
 *            --bootstrap-server localhost:9092 --topic task.created
 *   2) Во втором терминале — продюсер, отправьте 3 строки (Enter после каждой):
 *        docker compose exec -it kafka /opt/kafka/bin/kafka-console-producer.sh \
 *            --bootstrap-server localhost:9092 --topic task.created
 *        > {"id":1,"title":"Написать отчёт"}
 *        > {"id":2,"title":"Проверить логи"}
 *        > {"id":3,"title":"Закрыть спринт"}
 *   3) Остановите консьюмера (Ctrl+C) и запустите СНОВА без --from-beginning:
 *        - старые сообщения не появились (новая группа читает с конца)
 *   4) Запустите с --from-beginning:
 *        - все 3 сообщения прочитаны заново
 *   5) Зафиксируйте команды и вывод в COMMANDS.
 *
 * ОЖИДАЕМЫЙ ИТОГ: без --from-beginning консьюмер молчит, с --from-beginning печатает все
 *   ранее отправленные сообщения — данные никуда не делись после чтения.
 *
 * ЦЕЛЬ: прочувствовать разницу «очередь vs лог»: чтение не удаляет сообщение.
 *
 * ВАЖНО: console-consumer без --group каждый раз создаёт временную группу, поэтому её
 *   офсеты не сохраняются между запусками.
 *
 * ПОДСКАЗКА: для интерактивного ввода в продюсере нужен флаг -it у docker compose exec.
 */
public class Task03 {
    public static void main(String[] args) {
        String commands = """
                # TODO: producer/consumer через CLI
                # терминал 1 (consumer):
                # docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created
                #
                # терминал 2 (producer, вводите строки и Enter):
                # docker compose exec -it kafka /opt/kafka/bin/kafka-console-producer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created
                #
                # перечитать историю:
                # docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created --from-beginning
                #
                # НАБЛЮДЕНИЯ: без --from-beginning -> ... ; с --from-beginning -> ...
                """;
        System.out.println(commands);
    }
}
