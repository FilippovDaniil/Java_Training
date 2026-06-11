package m110_spring_test_security_async.practice.task07;

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

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task07.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class IntegrationSuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ReportService07 reports;
    // TODO: @MockBean PaymentGateway07 gateway;

    @Test
    void orders_require_auth() throws Exception {
        // TODO: mockMvc.perform(get("/api/orders")).andExpect(status().isUnauthorized());
    }

    @Test
    // TODO: @WithMockUser
    void user_can_list_orders() throws Exception {
        // TODO: mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }

    @Test
    // TODO: @WithMockUser(roles = "USER")
    void user_forbidden_on_stats() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }

    @Test
    // TODO: @WithMockUser(roles = "ADMIN")
    void admin_can_see_stats() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
    }

    @Test
    // TODO: @WithMockUser
    void order_mocks_payment() throws Exception {
        // TODO: when(gateway.charge(anyInt())).thenReturn("OK");
        // TODO: mockMvc.perform(post("/api/orders").contentType(APPLICATION_JSON).content("{\"amount\":100}"))
        //              .andExpect(status().isCreated());
        // TODO: verify(gateway).charge(100);
    }

    @Test
    void async_report_ready() throws Exception {
        // TODO: assertThat(reports.generate().get(2, TimeUnit.SECONDS)).isEqualTo("READY");
    }
}
