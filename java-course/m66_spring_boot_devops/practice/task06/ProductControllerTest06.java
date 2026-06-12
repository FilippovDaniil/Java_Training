package m66_spring_boot_devops.practice.task06;

/**
 * Задача 06 — Модуль 66: Slice-тест @WebMvcTest + MockMvc
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Запускать в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   1) ProductController06 и ProductService06 готовы — ИЗУЧИТЕ их.
 *   2) Класс ProductControllerTest06 пометьте @WebMvcTest(ProductController06.class).
 *      Поднимется ТОЛЬКО web-слой (контроллер), сервис нужно замокать.
 *   3) Внедрите @Autowired MockMvc и объявите @MockBean ProductService06.
 *   4) В тесте returnsProduct():
 *        - настройте мок: when(service.getName(1L)).thenReturn("Мышь")
 *        - выполните GET /api/products/1
 *        - проверьте status().isOk() и jsonPath("$.name").value("Мышь")
 *
 * ЦЕЛЬ: быстрый изолированный тест контроллера без поднятия всего приложения.
 *
 * ПОДСКАЗКА:
 *   import static ...MockMvcRequestBuilders.get;
 *   import static ...MockMvcResultMatchers.*;
 *   import static org.mockito.Mockito.when;
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: @WebMvcTest(ProductController06.class)
class ProductControllerTest06 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean ProductService06 service;

    @Test
    void returnsProduct() throws Exception {
        // TODO: when(service.getName(1L)).thenReturn("Мышь");
        // TODO: mockMvc.perform(get("/api/products/1"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.name").value("Мышь"));
    }
}
