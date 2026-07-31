package m120_kafka_producer_spring.practice.task05;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Три способа отправить запись и обработать результат.
 *
 * TODO 1: sendAsync — kafka.send(topic, key, value).whenComplete((res, ex) -> ...):
 *           при ex != null напечатать ошибку, иначе partition/offset; метод не блокируется.
 * TODO 2: sendSync — kafka.send(...).get(5, TimeUnit.SECONDS) в try/catch:
 *           InterruptedException (восстановить флаг прерывания!), ExecutionException, TimeoutException.
 * TODO 3: sendToMissing — та же отправка в несуществующий топик; поймать и напечатать причину сбоя.
 *
 * ВАЖНО: в catch InterruptedException обязательно Thread.currentThread().interrupt() —
 *   иначе теряется сигнал остановки потока (см. модуль 25).
 */
@Service
public class ReliablePublisher05 {

    private final KafkaTemplate<String, Object> kafka;

    public ReliablePublisher05(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void sendAsync(String topic, String key, Object value) {
        // TODO 1: асинхронная отправка + whenComplete
    }

    public void sendSync(String topic, String key, Object value) {
        // TODO 2: синхронная отправка с таймаутом и обработкой трёх исключений
    }

    public void sendToMissing(String topic, String key, Object value) {
        // TODO 3: отправка в отсутствующий топик, печать причины ошибки
    }
}
