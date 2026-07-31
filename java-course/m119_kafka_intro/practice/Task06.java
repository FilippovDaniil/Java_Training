package m119_kafka_intro.practice;

/**
 * Задача 06 — Модуль 119: хранение данных — retention, compaction, удаление топика
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Управляйте временем жизни данных в топике через kafka-configs.sh.
 *   1) Посмотреть текущие конфиги топика:
 *        kafka-configs.sh --bootstrap-server localhost:9092 \
 *            --entity-type topics --entity-name task.created --describe
 *   2) Сократить retention до 1 минуты (наблюдаемо в dev):
 *        kafka-configs.sh --bootstrap-server localhost:9092 \
 *            --entity-type topics --entity-name task.created \
 *            --alter --add-config retention.ms=60000
 *        - подождите и прочитайте топик с --from-beginning: старые записи удалены
 *   3) Вернуть значение по умолчанию:
 *        --alter --delete-config retention.ms
 *   4) Создать «таблицу состояний» — compacted-топик (хранит последнее значение на ключ):
 *        kafka-topics.sh --bootstrap-server localhost:9092 --create --topic task.state \
 *            --partitions 3 --replication-factor 1 --config cleanup.policy=compact
 *   5) Удалить учебный топик:
 *        kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic task.state
 *   6) Зафиксируйте команды и наблюдения в COMMANDS.
 *
 * ОЖИДАЕМЫЙ ИТОГ: после retention.ms=60000 и паузы --from-beginning больше не отдаёт старые
 *   записи; compacted-топик создан с cleanup.policy=compact; удалённый топик исчез из --list.
 *
 * ЦЕЛЬ: понять, что топик — не «вечная» и не «одноразовая» структура: политика хранения
 *   настраивается и определяет, что можно перечитать.
 *
 * ВАЖНО: compaction хранит ПОСЛЕДНЮЮ запись по каждому ключу; запись со значением null
 *   (tombstone) означает «ключ удалён». Для compaction ключ обязателен.
 *
 * ПОДСКАЗКА: удаление сегментов происходит не мгновенно — брокер проверяет их периодически.
 */
public class Task06 {
    public static void main(String[] args) {
        String commands = """
                # TODO: retention, compaction, удаление топика
                # docker compose exec kafka /opt/kafka/bin/kafka-configs.sh --bootstrap-server localhost:9092 \\
                #     --entity-type topics --entity-name task.created --describe
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-configs.sh --bootstrap-server localhost:9092 \\
                #     --entity-type topics --entity-name task.created --alter --add-config retention.ms=60000
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \\
                #     --create --topic task.state --partitions 3 --replication-factor 1 --config cleanup.policy=compact
                #
                # docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \\
                #     --delete --topic task.state
                #
                # НАБЛЮДЕНИЯ:
                # конфиги до изменения: ...
                # после retention.ms=60000 и паузы --from-beginning -> ...
                # cleanup.policy=compact нужен для: ...
                """;
        System.out.println(commands);
    }
}
