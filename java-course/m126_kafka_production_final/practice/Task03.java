package m126_kafka_production_final.practice;

/**
 * Задача 03 — Модуль 126: метрики, дашборд и алерты (Actuator + Micrometer + Prometheus)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с конфигами и правилами алертов + println).
 *
 * ЗАДАНИЕ:
 *   1) Конфигурация приложения (application.yml):
 *        management:
 *          endpoints.web.exposure.include: health,info,metrics,prometheus
 *          endpoint.health.probes.enabled: true            # /health/liveness, /health/readiness
 *          metrics.tags.application: task-tracker
 *          health.mail.enabled: false                      # иначе /health = 503 (см. CLAUDE.md)
 *      + зависимость micrometer-registry-prometheus.
 *   2) Свои метрики в коде (опишите, какие именно):
 *        - counter "events.published" с тегом topic;
 *        - counter "events.consumed" с тегами topic, result=ok|duplicate|failed;
 *        - counter "events.dlt" (записи, ушедшие в DLQ);
 *        - timer "events.processing" (время обработки одного события).
 *   3) Prometheus scrape-конфиг для приложения и для брокера (kafka-exporter / JMX exporter).
 *   4) Правила алертов (Prometheus rules) — напишите выражения:
 *        - ConsumerLagHigh: kafka_consumer_fetch_manager_records_lag_max > 10000 for 5m;
 *        - DltNotEmpty: increase(events_dlt_total[10m]) > 0;
 *        - ProducerErrors: rate(kafka_producer_record_error_total[5m]) > 0;
 *        - ProcessingSlow: histogram_quantile(0.99, ...events_processing...) > 2s for 10m;
 *        - UnderReplicated: kafka_server_replicamanager_underreplicatedpartitions > 0.
 *   5) Опишите состав дашборда Grafana (какие панели и почему).
 *
 * ОЖИДАЕМЫЙ ИТОГ: набор метрик и алертов, при котором о проблеме узнают мониторинг, а не
 *   пользователи: отставание, сбои отправки, непустой DLT, замедление обработки.
 *
 * ЦЕЛЬ: сделать событийный сервис наблюдаемым — это обязательная часть его готовности к проду.
 *
 * ВАЖНО: алерт на **ненулевой DLT** — самый важный из всех. Пустой DLT означает «всё обработано»;
 *   любая запись в нём — потерянное бизнес-событие, о котором никто не узнает без алерта.
 *
 * ПОДСКАЗКА: lag клиента доступен как метрика Micrometer автоматически (KafkaClientMetrics
 *   регистрируется Spring Boot). Дополнительно полезен внешний экспортёр lag'а по группам:
 *   он видит отставание даже когда потребитель полностью лежит и метрик не отдаёт.
 */
public class Task03 {
    public static void main(String[] args) {
        String observability = """
                # TODO 1: application.yml (management.endpoints, prometheus, health.mail.enabled=false)
                #
                # TODO 2: свои метрики
                # events.published{topic}          counter
                # events.consumed{topic,result}    counter  (ok|duplicate|failed)
                # events.dlt{topic}                counter
                # events.processing                timer
                #
                # TODO 3: prometheus.yml
                # scrape_configs:
                #   - job_name: task-tracker
                #     metrics_path: /actuator/prometheus
                #     static_configs: [{ targets: ["app:8080"] }]
                #   - job_name: kafka
                #     static_configs: [{ targets: ["kafka-exporter:9308"] }]
                #
                # TODO 4: alert rules
                # - alert: ConsumerLagHigh
                #   expr: kafka_consumer_fetch_manager_records_lag_max > 10000
                #   for: 5m
                # - alert: DltNotEmpty
                #   expr: increase(events_dlt_total[10m]) > 0
                # - alert: ProducerErrors ...
                # - alert: UnderReplicated ...
                #
                # TODO 5: панели Grafana (что и зачем)
                # 1) lag по группам   2) события/с по топикам   3) p50/p95/p99 обработки
                # 4) ошибки отправки  5) записи в DLT           6) ISR/under-replicated
                """;
        System.out.println(observability);
    }
}
