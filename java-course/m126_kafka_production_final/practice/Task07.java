package m126_kafka_production_final.practice;

/**
 * Задача 07 — Модуль 126: МИНИ-ПРОЕКТ «Production-ready шаблон событийного сервиса» (капстоун части 5)
 *
 * ФОРМАТ: носитель артефактов (.java + text-блок с итоговым шаблоном и чек-листом + println,
 *   компилируется bare-javac). Это финальная задача части 5 и курса.
 *
 * ЦЕЛЬ: собрать переиспользуемый шаблон, который можно скопировать в новый проект и сразу
 *       получить грамотный событийный сервис: структура, конфигурация dev/prod, локальный стек,
 *       манифесты K8s, метрики, тесты и чек-лист готовности.
 *
 * ЗАДАНИЕ — соберите в TEMPLATE все артефакты (опираясь на задачи 01–06 и модули 119–125):
 *   1) СТРУКТУРА ПРОЕКТА:
 *        api/ domain/ events/ messaging/{publisher,listener,config} projection/ ops/
 *        resources/{application.yml, application-prod.yml, asyncapi.yml}
 *        compose/ k8s/ tests/{unit,integration}
 *   2) application.yml (dev) и application-prod.yml (прод):
 *        - dev: bootstrap-servers localhost:29092, auto-offset-reset earliest, короткие ретраи;
 *        - prod: bootstrap-servers из env, acks=all, enable.idempotence=true, compression lz4,
 *          enable-auto-commit=false, ack-mode manual_immediate, isolation read_committed,
 *          SASL_SSL + SCRAM, management с prometheus.
 *   3) ЛОКАЛЬНЫЙ СТЕК (compose): app + db + kafka + kafka-ui с healthcheck и томами (модуль 119).
 *   4) K8S: Deployment (enableServiceLinks: false, initContainer wait-for-kafka, probes, limits),
 *      Secret, ConfigMap, ServiceMonitor (задача 02).
 *   5) КОД-КАРКАС (перечислите классы шаблона и их роль):
 *        EventEnvelope, <Domain>Event v1, TopicsConfig (NewTopic), EventPublisher (или OutboxRelay),
 *        <Domain>Listener (идемпотентный, ручной ack), ReliabilityConfig (backoff + DLQ),
 *        ProcessedEvents (дедупликация), Projector, DltAdminController (stats/reprocess).
 *   6) ТЕСТЫ (перечислите обязательный минимум из модуля 125):
 *        unit publisher (ключ/топик), unit listener (идемпотентность, ack),
 *        @EmbeddedKafka (publish->consume), retry/DLQ, contract approval,
 *        Testcontainers (порядок, реальный брокер) под тегом integration.
 *   7) ЧЕК-ЛИСТ production-готовности (перенесите из theory.md и отметьте, что уже сделано).
 *   8) ASYNCAPI: описание топиков и схем событий сервиса (модуль 122).
 *
 * ПРОВЕРКА (шаблон считается готовым, если):
 *   [ ] docker compose up -d поднимает весь стек одной командой;
 *   [ ] приложение стартует и создаёт/находит свои топики; /actuator/health = UP;
 *   [ ] POST в API приводит к событию в топике (проверено CLI/kafka-ui);
 *   [ ] потребитель обрабатывает событие ровно один раз при повторной доставке;
 *   [ ] сбойное событие после N попыток уходит в *.DLT с заголовками диагностики;
 *   [ ] /actuator/prometheus отдаёт метрики events.* и lag;
 *   [ ] ./gradlew test проходит без Docker; integration-тесты запускаются отдельным набором;
 *   [ ] чек-лист прода пройден целиком, а не «почти».
 *
 * ОЖИДАЕМЫЙ ИТОГ: артефакт, с которого начинается следующий проект. Дальше — только предметная
 *   логика: контракты событий и обработчики, а вся обвязка (надёжность, наблюдаемость,
 *   безопасность, тесты, деплой) уже на месте.
 *
 * ВАЖНО: шаблон бесполезен без последних двух пунктов чек-листа — метрик/алертов и runbook.
 *   Событийная система без наблюдаемости отличается от очереди в никуда только тем,
 *   что об этом узнают позже.
 *
 * ПОДСКАЗКА: сравните этот шаблон с Docker-шаблоном из модуля 118 — там упаковка одного сервиса,
 *   здесь взаимодействие нескольких. Вместе они дают полный набор для продуктового микросервиса.
 */
public class Task07 {
    public static void main(String[] args) {
        String template = """
                # TODO: production-ready шаблон событийного сервиса
                #
                # 1. СТРУКТУРА
                # service/
                #   api/ domain/ events/ messaging/{publisher,listener,config} projection/ ops/
                #   resources/{application.yml, application-prod.yml, asyncapi.yml}
                #   compose/ k8s/ tests/{unit,integration}
                #
                # 2. application-prod.yml (фрагмент)
                # spring.kafka:
                #   bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS}
                #   producer: { acks: all, properties: { enable.idempotence: true, compression.type: lz4 } }
                #   consumer: { enable-auto-commit: false, isolation-level: read_committed, ... }
                #   listener: { ack-mode: manual_immediate, concurrency: 3 }
                #   properties: { security.protocol: SASL_SSL, sasl.mechanism: SCRAM-SHA-512, ... }
                #
                # 3. COMPOSE: app + db + kafka + kafka-ui (healthcheck, тома)
                # 4. K8S: Deployment (enableServiceLinks: false, initContainer wait-for-kafka), Secret, ServiceMonitor
                #
                # 5. КАРКАС КОДА
                # EventEnvelope · <Domain>EventV1 · TopicsConfig · EventPublisher/OutboxRelay
                # <Domain>Listener · ReliabilityConfig (backoff + DLQ) · ProcessedEvents · Projector
                # DltAdminController (stats/reprocess)
                #
                # 6. ТЕСТЫ: unit publisher/listener · @EmbeddedKafka · retry+DLQ · contract approval
                #           · Testcontainers (@Tag("integration"))
                #
                # 7. ЧЕК-ЛИСТ ПРОДА (отметьте выполненное)
                # [ ] RF=3, min.insync.replicas=2, acks=all
                # [ ] auto.create.topics.enable=false, топики из IaC
                # [ ] ключ = id сущности; идемпотентный потребитель (eventId)
                # [ ] retry + DLQ; не-retryable ошибки не повторяются; ErrorHandlingDeserializer
                # [ ] outbox для критичных событий
                # [ ] метрики + алерты (lag, DLT, ошибки продюсера)
                # [ ] SASL/TLS + ACL минимальных прав; секреты в Secret/Vault
                # [ ] enableServiceLinks: false + initContainer wait-for-kafka
                # [ ] runbook инцидентов в репозитории
                # [ ] контракт событий описан (AsyncAPI) и версионируется
                #
                # 8. ASYNCAPI: каналы task.created / task.status.changed + схемы событий
                """;
        System.out.println(template);
    }
}
