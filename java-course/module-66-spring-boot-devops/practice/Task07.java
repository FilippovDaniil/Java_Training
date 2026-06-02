/**
 * Задача 07 — Модуль 66: МИНИ-ПРОЕКТ «Production-ready микросервис здоровья»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-actuator:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать «эксплуатационный» сервис — логирование + Actuator + кастомный
 *       health + slice-тест контроллера. Это итог трека 54–66.
 *
 * ЗАДАНИЕ (в одном файле):
 *
 *   ЧАСТЬ A — Сервис и логи:
 *     StatusService07 (@Service) с логгером SLF4J.
 *       - ping() → log.info("ping получен") и вернуть "pong".
 *       - queueSize() → вернуть число задач в очереди (эмуляция: верните 3).
 *
 *   ЧАСТЬ B — Кастомный HealthIndicator:
 *     QueueHealthIndicator07 (@Component, HealthIndicator).
 *       - если queueSize() < 100 → Health.up().withDetail("queue", <size>).build()
 *       - иначе                  → Health.down().withDetail("queue", <size>).build()
 *
 *   ЧАСТЬ C — Контроллер:
 *     StatusController07 (@RestController, @RequestMapping("/api/status")).
 *       - GET /api/status/ping → вернуть результат service.ping()  (200, тело "pong")
 *
 *   ЧАСТЬ D — application.properties:
 *     management.endpoints.web.exposure.include=health,info
 *     management.endpoint.health.show-details=always
 *     info.app.name=Status Service
 *     logging.level.root=INFO
 *
 *   ЧАСТЬ E — Slice-тест (StatusControllerTest07, @WebMvcTest):
 *     - @MockBean StatusService07; when(service.ping()).thenReturn("pong")
 *     - GET /api/status/ping → status().isOk() и content().string("pong")
 *
 * ПРОВЕРКА ВРУЧНУЮ:
 *   http://localhost:8080/api/status/ping       → pong
 *   http://localhost:8080/actuator/health         → UP + "queue":3
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ──► StatusController07 ──► StatusService07 (логи SLF4J)
 *                                           ▲
 *                          QueueHealthIndicator07 ──► /actuator/health
 *
 * ПОДСКАЗКА: контроллер с main здесь нужен для ручного запуска;
 *   тест @WebMvcTest поднимает только web-слой и мокирует сервис.
 */
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// ЧАСТЬ A — Сервис (каркас)
// ============================================================

@Service
class StatusService07 {
    // TODO: объявите логгер LoggerFactory.getLogger(StatusService07.class)

    public String ping() {
        // TODO: log.info("ping получен"); вернуть "pong"
        return null;
    }

    public int queueSize() {
        return 3; // эмуляция
    }
}

// ============================================================
// ЧАСТЬ B — HealthIndicator (каркас)
// ============================================================

// TODO: @Component
class QueueHealthIndicator07 implements HealthIndicator {
    private final StatusService07 service;
    QueueHealthIndicator07(StatusService07 service) { this.service = service; }

    @Override
    public Health health() {
        int size = service.queueSize();
        // TODO: size < 100 → Health.up().withDetail("queue", size).build()
        // TODO: иначе      → Health.down().withDetail("queue", size).build()
        return null;
    }
}

// ============================================================
// ЧАСТЬ C — Контроллер (каркас)
// ============================================================

@RestController
@RequestMapping("/api/status")
class StatusController07 {
    private final StatusService07 service;
    StatusController07(StatusService07 service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        // TODO: верните service.ping()
        return null;
    }
}

// ============================================================
// ЧАСТЬ E — Slice-тест (каркас)
// ============================================================

// TODO: @WebMvcTest(StatusController07.class)
class StatusControllerTest07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean StatusService07 service;

    @Test
    void pingReturnsPong() throws Exception {
        // TODO: when(service.ping()).thenReturn("pong");
        // TODO: mockMvc.perform(get("/api/status/ping"))
        //              .andExpect(status().isOk())
        //              .andExpect(content().string("pong"));
    }
}
