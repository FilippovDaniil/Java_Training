package m110_spring_test_security_async.practice.task03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task03.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class BasicIntegrationTest03 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    void valid_basic_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks").with(httpBasic("alice", "password")))
        //              .andExpect(status().isOk());
    }

    @Test
    void wrong_basic_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks").with(httpBasic("alice", "WRONG")))
        //              .andExpect(status().isUnauthorized());
    }
}
