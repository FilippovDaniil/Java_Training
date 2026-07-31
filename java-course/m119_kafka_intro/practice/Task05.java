package m119_kafka_intro.practice;

/**
 * Задача 05 — Модуль 119: consumer groups, распределение партиций, lag, сброс офсетов
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Разберитесь, как группы делят партиции и что такое отставание (lag).
 *   1) Запустите консьюмера В ГРУППЕ (терминал 1):
 *        kafka-console-consumer.sh --bootstrap-server localhost:9092 \
 *            --topic task.created --group notifier --from-beginning
 *   2) Посмотрите состояние группы (терминал 2):
 *        kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group notifier
 *        - разберите колонки: PARTITION, CURRENT-OFFSET, LOG-END-OFFSET, LAG, CONSUMER-ID
 *   3) Запустите ВТОРОГО консьюмера в той же группе и снова сделайте --describe:
 *        - партиции перераспределились между двумя членами группы (rebalance)
 *   4) Остановите обоих консьюмеров, отправьте 2 новых сообщения продюсером и сделайте --describe:
 *        - LAG стал > 0 (события есть, читать их некому)
 *   5) Перечитать топик группой заново (офсеты в начало; группа должна быть остановлена):
 *        kafka-consumer-groups.sh --bootstrap-server localhost:9092 \
 *            --group notifier --topic task.created --reset-offsets --to-earliest --execute
 *   6) Зафиксируйте команды и наблюдения в COMMANDS.
 *
 * ОЖИДАЕМЫЙ ИТОГ: с одним консьюмером все партиции у него; со вторым — поделены; после
 *   остановки группы и новых сообщений LAG растёт; после reset-offsets группа читает историю сначала.
 *
 * ЦЕЛЬ: понять consumer group как единицу масштабирования и lag как метрику здоровья.
 *
 * ВАЖНО: --reset-offsets работает только когда в группе нет активных консьюмеров;
 *   без --execute команда лишь показывает, что произошло бы (dry-run).
 *
 * ПОДСКАЗКА: LAG = LOG-END-OFFSET − CURRENT-OFFSET по каждой партиции; именно за этой
 *   величиной следят в проде (модуль 126).
 */
public class Task05 {
    public static void main(String[] args) {
        String commands = """
                # TODO: работа с consumer group
                # docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \\
                #     --bootstrap-server localhost:9092 --topic task.created --group notifier --from-beginning
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-consumer-groups.sh \\
                #     --bootstrap-server localhost:9092 --describe --group notifier
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-consumer-groups.sh \\
                #     --bootstrap-server localhost:9092 --group notifier --topic task.created \\
                #     --reset-offsets --to-earliest --execute
                #
                # НАБЛЮДЕНИЯ:
                # 1 консьюмер  -> партиции: ...
                # 2 консьюмера -> партиции: ...
                # после остановки группы LAG = ...
                # после reset-offsets -> ...
                """;
        System.out.println(commands);
    }
}
