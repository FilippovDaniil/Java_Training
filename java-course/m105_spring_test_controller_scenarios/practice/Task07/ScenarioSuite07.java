package m105_spring_test_controller_scenarios.practice.task07;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController07.class)
// TODO: @Import(Advice07.class)
class ScenarioSuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService07 service;

    @Test
    void create_blank_400() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"\"}"))
        //              .andExpect(status().isBadRequest());
        // TODO: verify(service, never()).create(any());
    }

    @Test
    void create_ok_201() throws Exception {
        // TODO: when(service.create(any())).thenReturn(1L);
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"Кофе\"}"))
        //              .andExpect(status().isCreated());
    }

    @Test
    void get_missing_404() throws Exception {
        // TODO: when(service.find(99L)).thenThrow(new TaskNotFound07(99L));
        // TODO: mockMvc.perform(get("/api/tasks/99"))
        //              .andExpect(status().isNotFound())
        //              .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void list_paged() throws Exception {
        // TODO: when(service.list(any())).thenReturn(
        // TODO:     new PageImpl<>(List.of(new TaskDto07(1L, "Кофе")), PageRequest.of(0, 10), 1));
        // TODO: mockMvc.perform(get("/api/tasks").param("page", "0").param("size", "10"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void upload_201() throws Exception {
        // TODO: MockMultipartFile file = new MockMultipartFile("file", "n.txt", "text/plain", "x".getBytes());
        // TODO: when(service.store(any())).thenReturn("n.txt");
        // TODO: mockMvc.perform(multipart("/api/tasks/1/attachments").file(file))
        //              .andExpect(status().isCreated())
        //              .andExpect(jsonPath("$.filename").value("n.txt"));
    }
}
