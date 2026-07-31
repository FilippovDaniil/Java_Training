package m121_kafka_consumer_spring.practice.task07;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Управление слушателями в рантайме: техническая пауза без остановки приложения.
 *
 * TODO 1: POST /api/listeners/{id}/pause  — найти контейнер в registry по id и вызвать pause();
 *         если контейнера нет — 404.
 * TODO 2: POST /api/listeners/{id}/resume — resume(); 404, если контейнера нет.
 * TODO 3: вернуть 200 с текущим состоянием (isPauseRequested / isRunning).
 *
 * ЗАЧЕМ В ПРОДЕ: внешний сервис (почта, платёжный шлюз) деградировал — ставим потребителя на
 *   паузу, события накапливаются в топике (это нормально: лог хранит их до retention),
 *   после восстановления снимаем паузу и догоняем LAG. Данные не теряются.
 */
@RestController
@RequestMapping("/api/listeners")
public class ListenerAdminController07 {

    private final KafkaListenerEndpointRegistry registry;

    public ListenerAdminController07(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @PostMapping("/{id}/pause")
    ResponseEntity<?> pause(@PathVariable String id) {
        // TODO 1 + TODO 3
        return ResponseEntity.status(501).build();
    }

    @PostMapping("/{id}/resume")
    ResponseEntity<?> resume(@PathVariable String id) {
        // TODO 2 + TODO 3
        return ResponseEntity.status(501).build();
    }
}
