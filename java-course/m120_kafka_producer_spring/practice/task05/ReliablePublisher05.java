package m120_kafka_producer_spring.practice.task05;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    /** Асинхронная отправка: метод возвращается сразу, результат — в callback. */
    public void sendAsync(String topic, String key, Object value) {
        kafka.send(topic, key, value).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.printf("async: ошибка отправки в %s: %s%n", topic, ex.getMessage());
                return;
            }
            var md = result.getRecordMetadata();
            System.out.printf("async: подтверждено partition %d offset %d%n",
                    md.partition(), md.offset());
        });
        System.out.println("async: метод вернулся");
    }

    /** Синхронная отправка: блокируемся до подтверждения (или таймаута). */
    public void sendSync(String topic, String key, Object value) {
        try {
            var result = kafka.send(topic, key, value).get(5, TimeUnit.SECONDS);
            var md = result.getRecordMetadata();
            System.out.printf("sync: подтверждено partition %d offset %d%n",
                    md.partition(), md.offset());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();           // восстановить флаг
            System.err.println("sync: прервано: " + e.getMessage());
        } catch (ExecutionException e) {
            System.err.println("sync: ошибка отправки: " + e.getCause().getMessage());
        } catch (TimeoutException e) {
            System.err.println("sync: таймаут ожидания подтверждения");
        }
    }

    /** Отправка в отсутствующий топик — печатаем причину сбоя. */
    public void sendToMissing(String topic, String key, Object value) {
        try {
            kafka.send(topic, key, value).get(5, TimeUnit.SECONDS);
            System.out.println("sendToMissing: неожиданно подтверждено");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("sendToMissing: прервано: " + e.getMessage());
        } catch (ExecutionException e) {
            System.err.printf("ошибка отправки в %s: %s%n", topic, e.getCause().getMessage());
        } catch (TimeoutException e) {
            System.err.printf("ошибка отправки в %s: таймаут ожидания%n", topic);
        }
    }
}
