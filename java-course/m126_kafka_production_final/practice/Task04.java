package m126_kafka_production_final.practice;

/**
 * Задача 04 — Модуль 126: безопасность — SASL/SCRAM, TLS, ACL по минимальным правам
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с конфигами и командами + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   1) Опишите клиентскую конфигурацию защищённого подключения (application-prod.yml):
 *        spring.kafka.properties:
 *          security.protocol: SASL_SSL
 *          sasl.mechanism: SCRAM-SHA-512
 *          sasl.jaas.config: org.apache.kafka.common.security.scram.ScramLoginModule required
 *                            username="task-tracker" password="${KAFKA_PASSWORD}";
 *          ssl.truststore.location: /etc/kafka/certs/truststore.jks
 *          ssl.truststore.password: ${TRUSTSTORE_PASSWORD}
 *      Секреты — только через переменные окружения из Secret/Vault.
 *   2) Создайте пользователей (SCRAM) для двух сервисов:
 *        kafka-configs.sh --bootstrap-server kafka:9092 --alter \
 *            --add-config 'SCRAM-SHA-512=[password=...]' --entity-type users --entity-name task-tracker
 *        (то же для notifier)
 *   3) Настройте ACL по принципу минимальных прав:
 *        - task-tracker: Write в task.created, task.status.changed (и ничего больше);
 *        - notifier: Read из task.created, task.status.changed + Read группы notifier;
 *        - notifier: Write в task.created.DLT (для DeadLetterPublishingRecoverer!);
 *        - никому: DeleteTopic и Alter в проде (только админскому принципалу).
 *      Команды kafka-acls.sh --add --allow-principal User:... --operation ... --topic ... --group ...
 *   4) Опишите проверку: что произойдёт, если notifier попробует писать в task.created
 *      (ожидается TopicAuthorizationException) — и почему это правильно.
 *   5) Перечислите, что должно попасть в Secret, а что может остаться в ConfigMap.
 *
 * ОЖИДАЕМЫЙ ИТОГ: два сервиса подключаются под своими учётными записями по TLS и имеют доступ
 *   строго к своим топикам и группам.
 *
 * ЦЕЛЬ: закрыть типовую дыру «все сервисы ходят под одним пользователем с полными правами».
 *
 * ВАЖНО: про право Write в DLT забывают чаще всего. Потребитель с DLQ обязан иметь права на
 *   запись в свой dead-letter топик, иначе восстановитель сам упадёт — и сбойное событие
 *   потеряется тихо, в момент, когда оно уже и так проблемное.
 *
 * ПОДСКАЗКА: пароли SCRAM хранятся на брокере (в метаданных кластера), поэтому смена пароля
 *   сервиса — операция на брокере + обновление Secret, без пересборки образа приложения.
 */
public class Task04 {
    public static void main(String[] args) {
        String security = """
                # TODO 1: application-prod.yml — SASL_SSL + SCRAM-SHA-512 + truststore
                #
                # TODO 2: создание пользователей SCRAM
                # kafka-configs.sh --bootstrap-server kafka:9092 --alter \\
                #     --add-config 'SCRAM-SHA-512=[password=***]' --entity-type users --entity-name task-tracker
                #
                # TODO 3: ACL (минимальные права)
                # kafka-acls.sh --add --allow-principal User:task-tracker --operation Write --topic task.created
                # kafka-acls.sh --add --allow-principal User:notifier --operation Read --topic task.created
                # kafka-acls.sh --add --allow-principal User:notifier --operation Read --group notifier
                # kafka-acls.sh --add --allow-principal User:notifier --operation Write --topic task.created.DLT
                # kafka-acls.sh --list
                #
                # TODO 4: проверка запрета
                # notifier пишет в task.created -> ожидаем: ...
                #
                # TODO 5: Secret vs ConfigMap
                # Secret:    ...
                # ConfigMap: ...
                """;
        System.out.println(security);
    }
}
