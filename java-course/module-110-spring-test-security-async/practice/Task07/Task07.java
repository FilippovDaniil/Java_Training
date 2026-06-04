/**
 * Задача 07 — Модуль 110: МИНИ-ПРОЕКТ «Интеграционный сьют: security + async + внешняя граница»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test, spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test. КАПСТОУН блока Spring Test (101–110).
 *
 * ЦЕЛЬ: одним сьютом в ПОЛНОМ контексте покрыть production-аспекты: защита доступа,
 *       асинхронная операция, изоляция от внешнего платёжного шлюза.
 *
 * ПРИЛОЖЕНИЕ Task07 (готово ниже):
 *   GET  /api/orders          — список (аутентифицированный);
 *   POST /api/orders {amount}  — создать заказ: зовёт PaymentGateway07.charge (внешняя граница);
 *   GET  /api/admin/stats      — только ADMIN;
 *   ReportService07.generate() — @Async → CompletableFuture<String> "READY".
 *
 * ЗАДАНИЕ — @SpringBootTest(classes = Task07.class) + @AutoConfigureMockMvc,
 *           @Autowired MockMvc, @Autowired ReportService07 reports, @MockBean PaymentGateway07 gateway:
 *
 *   1) orders_require_auth():
 *        mockMvc.perform(get("/api/orders")) → isUnauthorized();
 *
 *   2) user_can_list_orders():  @WithMockUser
 *        mockMvc.perform(get("/api/orders")) → isOk();
 *
 *   3) admin_only_stats():
 *        @WithMockUser(roles="USER") get("/api/admin/stats") → isForbidden() (в отдельном методе);
 *        @WithMockUser(roles="ADMIN") get("/api/admin/stats") → isOk() (в отдельном методе);
 *
 *   4) order_mocks_payment():  @WithMockUser
 *        when(gateway.charge(anyInt())).thenReturn("OK");
 *        post("/api/orders") {"amount":100} → isCreated();
 *        verify(gateway).charge(100);     // реальный платёж не ушёл
 *
 *   5) async_report_ready():
 *        assertThat(reports.generate().get(2, TimeUnit.SECONDS)).isEqualTo("READY");
 *
 * ОЖИДАЕМЫЙ ИТОГ: ключевые production-сценарии (доступ, async, внешняя граница) покрыты в интеграции.
 *
 * ПОДСКАЗКА: соедините задачи 01–02 (security), 04 (async), 05 (внешняя граница).
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Task07 {
    public static void main(String[] args) { SpringApplication.run(Task07.class, args); }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .csrf(c -> c.disable());
        return http.build();
    }
}
