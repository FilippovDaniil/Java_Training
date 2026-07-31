package m123_kafka_reliability.practice.task07;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final KafkaTemplate<String, Object> kafka;
    private final ProcessedEvents07 processedEvents;
    private final NotificationListener07 listener;

    public DltAdminController07(KafkaTemplate<String, Object> kafka,
                                ProcessedEvents07 processedEvents,
                                NotificationListener07 listener) {
        this.kafka = kafka;
        this.processedEvents = processedEvents;
        this.listener = listener;
    }

    @GetMapping("/stats")
    ResponseEntity<?> stats() {
        // TODO 1
        return ResponseEntity.status(501).build();
    }

    @PostMapping("/reprocess")
    ResponseEntity<?> reprocess() {
        // TODO 2-4
        return ResponseEntity.status(501).build();
    }
}
