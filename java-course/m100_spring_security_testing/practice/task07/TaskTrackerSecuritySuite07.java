package m100_spring_security_testing.practice.task07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ-СЬЮТ (каркас) ---
// TODO: @WebMvcTest(controllers = {PublicController07.class, TaskController07.class, AdminController07.class})
// TODO: @Import({SecurityConfig07.class, TaskOwnership07.class})
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@WebMvcTest(controllers = {PublicController07.class, TaskController07.class, AdminController07.class})
@Import({SecurityConfig07.class, TaskOwnership07.class})
class TaskTrackerSecuritySuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    @Autowired MockMvc mockMvc;

    // --- публичный ---
    @Test
    void public_no_auth_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/public/health")).andExpect(status().isOk());
        mockMvc.perform(get("/api/public/health")).andExpect(status().isOk());
    }

    // --- защищённый ---
    @Test
    void tasks_anonymous_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
    }

    @Test
    // TODO: @WithMockUser
    @WithMockUser
    void tasks_user_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
        mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }

    // --- ролевой ---
    @Test
    // TODO: @WithMockUser(roles = "USER")
    @WithMockUser(roles = "USER")
    void admin_user_403() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
        mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }

    @Test
    // TODO: @WithMockUser(roles = "ADMIN")
    @WithMockUser(roles = "ADMIN")
    void admin_admin_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
        mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
    }

    // --- ownership ---
    @Test
    // TODO: @WithMockUser("alice")
    @WithMockUser("alice")
    void owner_can_delete_204() throws Exception {
        // TODO: mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isNoContent());
        mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isNoContent());
    }

    @Test
    // TODO: @WithMockUser("bob")
    @WithMockUser("bob")
    void stranger_cannot_403() throws Exception {
        // TODO: mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isForbidden());
    }

    @Test
    // TODO: @WithMockUser(username = "bob", roles = "ADMIN")
    @WithMockUser(username = "bob", roles = "ADMIN")
    void admin_can_delete_204() throws Exception {
        // TODO: mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isNoContent());
        mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isNoContent());
    }
}
