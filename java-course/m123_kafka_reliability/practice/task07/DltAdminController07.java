package m123_kafka_reliability.practice.task07;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Эксплуатационный контур DLQ: посмотреть статистику и переиграть сбойные события.
 *
 * TODO 1: GET /api/dlt/stats — вернуть {processed, duplicates, failures, dltCount}.
 * TODO 2: POST /api/dlt/reprocess — прочитать записи из "task.created.DLT" и отправить их
 *         обратно в "task.created", добавив заголовок reprocessed=true.
 * TODO 3: защита от цикла — записи с заголовком reprocessed=true повторно НЕ переигрывать,
 *         вернуть их количество в ответе как skipped.
 * TODO 4: ответ 200 с итогом {reprocessed: N, skipped: M}.
 *
 * КАК ПРОЧИТАТЬ DLT ПО ТРЕБОВАНИЮ: слушатель DLT с autoStartup = "false" + запуск/остановка
 *   через KafkaListenerEndpointRegistry (модуль 121, задача 07), либо разовый KafkaConsumer,
 *   созданный из ConsumerFactory. Второй вариант проще для «одноразового» вычитывания.
 *
 * ЗАЧЕМ ТАКОЙ ЭНДПОИНТ: инцидент выглядит так — «внешний SMTP лежал 20 минут, 300 событий
 *   в DLT». Причина устранена, и нужно вернуть события в обработку без ручной работы с CLI.
 *   Обязательные условия безопасности: идемпотентный обработчик (иначе дубли) и защита от
 *   бесконечного цикла reprocess -> сбой -> DLT -> reprocess.
 */
@RestController
@RequestMapping("/api/dlt")
public class DltAdminController07 {

    private static final String DLT_TOPIC = "task.created.DLT";
    private static final String MAIN_TOPIC = "task.created";

    private final KafkaTemplate<String, Object> kafka;
    private final ProcessedEvents07 processedEvents;
    private final NotificationListener07 listener;
    private final ConsumerFactory<String, Object> consumerFactory;

    public DltAdminController07(KafkaTemplate<String, Object> kafka,
                                ProcessedEvents07 processedEvents,
                                NotificationListener07 listener,
                                ConsumerFactory<String, Object> consumerFactory) {
        this.kafka = kafka;
        this.processedEvents = processedEvents;
        this.listener = listener;
        this.consumerFactory = consumerFactory;
    }

    @GetMapping("/stats")
    ResponseEntity<?> stats() {
        return ResponseEntity.ok(Map.of(
                "processed", processedEvents.processedCount(),
                "duplicates", processedEvents.duplicateCount(),
                "failures", listener.failureCount(),
                "dltCount", 0   // в реальном проекте — из Micrometer/DLT-слушателя
        ));
    }

    @PostMapping("/reprocess")
    ResponseEntity<?> reprocess() {
        int reprocessed = 0;
        int skipped = 0;

        try (KafkaConsumer<String, Object> consumer = (KafkaConsumer<String, Object>) consumerFactory.createConsumer()) {
            consumer.subscribe(List.of(DLT_TOPIC));
            consumer.poll(Duration.ofSeconds(1));   // вступить в группу
            consumer.seekToBeginning(consumer.assignment());

            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, Object> rec : records) {
                var reprocessedHeader = rec.headers().lastHeader("reprocessed");
                if (reprocessedHeader != null
                        && "true".equals(new String(reprocessedHeader.value(), StandardCharsets.UTF_8))) {
                    skipped++;
                    continue;
                }

                ProducerRecord<String, Object> out = new ProducerRecord<>(MAIN_TOPIC, rec.key(), rec.value());
                out.headers().add(new RecordHeader("reprocessed",
                        "true".getBytes(StandardCharsets.UTF_8)));
                kafka.send(out);
                reprocessed++;
            }
            kafka.flush();
        }

        return ResponseEntity.ok(Map.of("reprocessed", reprocessed, "skipped", skipped));
    }
}
