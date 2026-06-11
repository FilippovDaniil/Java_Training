package m108_spring_test_full_context.practice.task01;

/**
 * Задача 01 — Модуль 108: @SpringBootTest + @AutoConfigureMockMvc (полный контекст, без сети)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Приложение Task01 (контроллер+сервис+репозиторий, готово) тестируем ЦЕЛИКОМ через MockMvc:
 *     1) @SpringBootTest(classes = Task01.class) + @AutoConfigureMockMvc; @Autowired MockMvc.
 *     2) list_endpoint_full_stack():
 *          mockMvc.perform(get("/api/tasks"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(jsonPath("$").isArray());
 *
 * ЦЕЛЬ: понять отличие от @WebMvcTest — здесь РЕАЛЬНЫЕ сервис и репозиторий (не моки),
 *       но без сетевого слоя (webEnvironment=MOCK по умолчанию).
 *
 * ВАЖНО: без @AutoConfigureMockMvc бин MockMvc не будет доступен в @SpringBootTest.
 *
 * ПОДСКАЗКА: запрос проходит весь стек контроллер→сервис→репозиторий→H2.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Task01 {
    public static void main(String[] args) { SpringApplication.run(Task01.class, args); }
}
