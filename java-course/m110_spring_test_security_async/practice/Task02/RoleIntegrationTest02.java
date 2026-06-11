package m110_spring_test_security_async.practice.task02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task02.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class RoleIntegrationTest02 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    // TODO: @WithMockUser(roles = "ADMIN")
    void admin_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
    }

    @Test
    // TODO: @WithMockUser(roles = "USER")
    void user_403() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }

    @Test
    void anon_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isUnauthorized());
    }
}
