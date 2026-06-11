package m105_spring_test_controller_scenarios.practice.task07;

/**
 * Задача 07 — Модуль 105: МИНИ-ПРОЕКТ «Сценарный тест-сьют контроллера Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-validation, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: одним сьютом покрыть типовые сценарии REST: валидация (400), ошибка (404 ProblemDetail),
 *       пагинация и загрузка файла. Капстоун первой части Spring Test (101–105).
 *
 * КОНТРОЛЛЕР TaskController07 + advice (готовы ниже):
 *   POST /api/tasks                       → @Valid (400 при пустом title), иначе 201;
 *   GET  /api/tasks/{id}                  → 404 (TaskNotFound07 → advice → ProblemDetail);
 *   GET  /api/tasks                       → Page<TaskDto07>;
 *   POST /api/tasks/{id}/attachments      → multipart upload, 201.
 *
 * ЗАДАНИЕ — @WebMvcTest(TaskController07.class) + @Import(Advice07.class),
 *           @Autowired MockMvc, @MockBean TaskService07:
 *
 *   1) create_blank_400(): POST {"title":""} → isBadRequest(); verify(service, never()).create(any()).
 *   2) create_ok_201():    when(service.create(any())).thenReturn(1L);
 *                          POST {"title":"Кофе"} → isCreated().
 *   3) get_missing_404():  when(service.find(99L)).thenThrow(new TaskNotFound07(99L));
 *                          GET /api/tasks/99 → isNotFound(), jsonPath("$.status").value(404).
 *   4) list_paged():       when(service.list(any())).thenReturn(
 *                              new PageImpl<>(List.of(new TaskDto07(1L,"Кофе")), PageRequest.of(0,10), 1));
 *                          GET /api/tasks?page=0&size=10 → isOk(), $.totalElements = 1.
 *   5) upload_201():       MockMultipartFile file = new MockMultipartFile("file","n.txt","text/plain","x".getBytes());
 *                          when(service.store(any())).thenReturn("n.txt");
 *                          multipart("/api/tasks/1/attachments").file(file) → isCreated(), $.filename="n.txt".
 *
 * ОЖИДАЕМЫЙ ИТОГ: ключевые сценарии контроллера зафиксированы; регрессия в контракте будет поймана.
 *
 * ПОДСКАЗКА: соедините задачи 01 (валидация), 02 (ошибка), 03 (пагинация), 05 (upload).
 */

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

record CreateTask07(@NotBlank String title) {}
