package m126_kafka_production_final.practice;

/**
 * Задача 02 — Модуль 126: событийный сервис в Kubernetes (ловушки enableServiceLinks и порядка старта)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с манифестами K8s + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Напишите манифесты для развёртывания приложения-потребителя в Kubernetes (namespace
 *   task-tracker) и впишите их в MANIFESTS:
 *     1) Deployment app:
 *          - enableServiceLinks: false                      # иначе KAFKA_PORT ломает клиента/брокер
 *          - initContainers: wait-for-kafka (busybox: until nc -z kafka 9092; do sleep 2; done)
 *          - env: SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092,
 *                 SPRING_PROFILES_ACTIVE=prod,
 *                 SPRING_KAFKA_PROPERTIES_SASL_JAAS_CONFIG из Secret (secretKeyRef)
 *          - probes: readiness /actuator/health/readiness, liveness /actuator/health/liveness
 *          - resources: requests/limits (память с учётом MaxRAMPercentage, модуль 117)
 *     2) Secret kafka-credentials (stringData с JAAS-конфигом) — НЕ ConfigMap.
 *     3) ConfigMap с некритичными настройками (имена топиков, размер батча).
 *     4) ServiceMonitor (или аннотации prometheus.io/scrape) для сбора /actuator/prometheus.
 *   Затем — команды проверки:
 *     kubectl apply -f k8s/ -n task-tracker
 *     kubectl get pods -n task-tracker
 *     kubectl logs deployment/app -n task-tracker -f
 *     kubectl describe pod <pod> -n task-tracker | grep -A5 Events
 *
 * ОЖИДАЕМЫЙ ИТОГ: приложение стартует ПОСЛЕ готовности брокера, не падает из-за переменных
 *   Service links, читает креды из Secret и отдаёт метрики Prometheus.
 *
 * ЦЕЛЬ: перенести событийный сервис в оркестратор, не наступив на две известные грабли.
 *
 * ВАЖНО (из рабочей практики, см. ~/.claude/CLAUDE.md):
 *   - `KAFKA_PORT`, создаваемый Service links, ломает конфигурацию Kafka-клиента и брокера:
 *     под падает с Exit Code 1 через пару секунд. Лечится ТОЛЬКО enableServiceLinks: false;
 *   - приложение с @KafkaListener подключается к брокеру НА СТАРТЕ (eagerly), поэтому
 *     initContainer wait-for-kafka обязателен, иначе CrashLoopBackOff при совместном деплое;
 *   - /actuator/health должен быть доступен probe без авторизации (permitAll), иначе
 *     readinessProbe получит 401 и под будет перезапускаться бесконечно.
 *
 * ПОДСКАЗКА: сам кластер Kafka в проде разворачивают оператором Strimzi (KafkaTopic/KafkaUser как
 *   ресурсы K8s), а не самописным StatefulSet — тогда топики и ACL описываются декларативно.
 */
public class Task02 {
    public static void main(String[] args) {
        String manifests = """
                # TODO 1: Deployment app
                # apiVersion: apps/v1
                # kind: Deployment
                # spec:
                #   template:
                #     spec:
                #       enableServiceLinks: false            # ловушка KAFKA_PORT
                #       initContainers:
                #         - name: wait-for-kafka
                #           image: busybox:1.36
                #           command: ['sh','-c','until nc -z kafka 9092; do echo waiting; sleep 2; done']
                #       containers:
                #         - name: app
                #           env:
                #             - name: SPRING_KAFKA_BOOTSTRAP_SERVERS
                #               value: "kafka:9092"
                #             - name: SPRING_KAFKA_PROPERTIES_SASL_JAAS_CONFIG
                #               valueFrom: { secretKeyRef: { name: kafka-credentials, key: jaas } }
                #           readinessProbe: { httpGet: { path: /actuator/health/readiness, port: 8080 } }
                #           livenessProbe:  { httpGet: { path: /actuator/health/liveness,  port: 8080 } }
                #           resources: { requests: {...}, limits: {...} }
                #
                # TODO 2: Secret kafka-credentials (jaas)
                # TODO 3: ConfigMap с именами топиков
                # TODO 4: ServiceMonitor / аннотации prometheus.io/scrape
                #
                # команды:
                # kubectl apply -f k8s/ -n task-tracker
                # kubectl get pods -n task-tracker
                # kubectl logs deployment/app -n task-tracker -f
                """;
        System.out.println(manifests);
    }
}
