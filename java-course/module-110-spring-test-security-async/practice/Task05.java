/**
 * Задача 05 — Модуль 110: изоляция от внешней интеграции (@MockBean границы)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-test (+ security/security-test, если защищаете эндпоинт).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   OrderController вызывает внешний PaymentGateway05 (готов). В тесте РЕАЛЬНЫЙ платёж недопустим —
 *   замокайте границу:
 *     1) @SpringBootTest(classes = Task05.class) + @AutoConfigureMockMvc;
 *        @Autowired MockMvc; @MockBean PaymentGateway05 gateway.
 *     2) places_order_without_real_payment():
 *          when(gateway.charge(anyInt())).thenReturn("OK");
 *          mockMvc.perform(post("/api/orders").contentType(APPLICATION_JSON).content("{\"amount\":100}"))
 *                 .andExpect(status().isCreated());
 *          verify(gateway).charge(100);    // граница вызвана, но реальный платёж НЕ ушёл
 *
 * ЦЕЛЬ: усвоить правило — в интеграционном тесте мокают ТОЛЬКО внешние границы (платежи, почта, API).
 *
 * ВАЖНО: внутренние слои (сервис/репозиторий) остаются настоящими; мок — лишь на стороннем шлюзе.
 *
 * ПОДСКАЗКА: для имитации HTTP-сервиса целиком применяют WireMock (фейковый эндпоинт) — та же идея на уровне сети.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) { SpringApplication.run(Task05.class, args); }
}

// Внешняя граница: в реальности ходит в платёжный шлюз.
interface PaymentGateway05 {
    String charge(int amount);
}

record CreateOrder05(int amount) {}

@Service
class OrderService05 {
    private final PaymentGateway05 gateway;
    OrderService05(PaymentGateway05 gateway) { this.gateway = gateway; }
    String place(int amount) { return gateway.charge(amount); }
}

@RestController
@RequestMapping("/api/orders")
class OrderController05 {
    private final OrderService05 service;
    OrderController05(OrderService05 service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String create(@RequestBody CreateOrder05 req) { return service.place(req.amount()); }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task05.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class ExternalIntegrationTest05 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean PaymentGateway05 gateway;

    @Test
    void places_order_without_real_payment() throws Exception {
        // TODO: when(gateway.charge(anyInt())).thenReturn("OK");
        // TODO: mockMvc.perform(post("/api/orders").contentType(APPLICATION_JSON).content("{\"amount\":100}"))
        //              .andExpect(status().isCreated());
        // TODO: verify(gateway).charge(100);
    }
}
