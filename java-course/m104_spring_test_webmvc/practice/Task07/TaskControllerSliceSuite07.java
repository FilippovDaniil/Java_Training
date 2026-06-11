package m104_spring_test_webmvc.practice.task07;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController07.class)
// TODO: @Import(TaskAdvice07.class)
class TaskControllerSliceSuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ObjectMapper objectMapper;
    // TODO: @MockBean TaskService07 service;

    @Test
    void lists_all() throws Exception {
        // TODO: when(service.all()).thenReturn(List.of(new TaskDto07(1L, "Кофе", "NEW")));
        // TODO: mockMvc.perform(get("/api/tasks"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.length()").value(1))
        //              .andExpect(jsonPath("$[0].title").value("Кофе"));
    }

    @Test
    void gets_one() throws Exception {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto07(1L, "Кофе", "NEW"));
        // TODO: mockMvc.perform(get("/api/tasks/1"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.status").value("NEW"));
    }

    @Test
    void get_missing_404() throws Exception {
        // TODO: when(service.find(99L)).thenThrow(new NoSuchElementException("нет"));
        // TODO: mockMvc.perform(get("/api/tasks/99")).andExpect(status().isNotFound());
    }

    @Test
    void creates_201_location() throws Exception {
        // TODO: when(service.create(any())).thenReturn(new TaskDto07(10L, "Чай", "NEW"));
        // TODO: String body = objectMapper.writeValueAsString(new CreateTask07("Чай"));
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content(body))
        //              .andExpect(status().isCreated())
        //              .andExpect(header().string("Location", "/api/tasks/10"))
        //              .andExpect(jsonPath("$.id").value(10));
    }
}
