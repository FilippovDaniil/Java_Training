package m104_spring_test_webmvc.practice.task07;

/**
 * Задача 07 — Модуль 104: МИНИ-ПРОЕКТ «Полный slice-тест контроллера Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: покрыть @WebMvcTest'ом весь CRUD контроллера задач: список, чтение по id (200 и 404),
 *       создание (201 + Location). Сервис замокан, БД нет.
 *
 * КОНТРОЛЛЕР TaskController07 (готов ниже):
 *   GET  /api/tasks        → список;
 *   GET  /api/tasks/{id}   → задача или 404 (сервис бросает NoSuchElementException → advice → 404);
 *   POST /api/tasks        → 201 + Location.
 *
 * ЗАДАНИЕ — напишите тест-класс @WebMvcTest(TaskController07.class)
 *           @Import(TaskAdvice07.class) с @Autowired MockMvc, @Autowired ObjectMapper, @MockBean TaskService07:
 *
 *   1) lists_all():
 *        when(service.all()).thenReturn(List.of(new TaskDto07(1L,"Кофе","NEW")));
 *        get("/api/tasks") → isOk(), jsonPath("$.length()").value(1), $[0].title = "Кофе".
 *
 *   2) gets_one():
 *        when(service.find(1L)).thenReturn(new TaskDto07(1L,"Кофе","NEW"));
 *        get("/api/tasks/1") → isOk(), $.status = "NEW".
 *
 *   3) get_missing_404():
 *        when(service.find(99L)).thenThrow(new NoSuchElementException("нет"));
 *        get("/api/tasks/99") → isNotFound().
 *
 *   4) creates_201_location():
 *        when(service.create(any())).thenReturn(new TaskDto07(10L,"Чай","NEW"));
 *        post("/api/tasks") c JSON {"title":"Чай"} → isCreated(), header Location "/api/tasks/10", $.id = 10.
 *
 * ОЖИДАЕМЫЙ ИТОГ: весь web-контракт контроллера зафиксирован быстрыми срезовыми тестами.
 *
 * ПОДСКАЗКА: соедините задачи 01 (GET), 02 (POST+Location), 04 (массив), 03 (verify при желании).
 */

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

record CreateTask07(String title) {}
