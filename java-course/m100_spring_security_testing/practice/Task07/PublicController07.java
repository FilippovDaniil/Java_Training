package m100_spring_security_testing.practice.task07;

/**
 * Задача 07 — Модуль 100: МИНИ-ПРОЕКТ «Полный security-тест-сьют Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * ЦЕЛЬ: одним тест-классом закрыть все типовые сценарии доступа Task Tracker —
 *       публичные, защищённые, ролевые и ownership-проверки (капстоун блока Security 93–100).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * РОЛИ ДОСТУПА (конфиг + контроллеры готовы ниже):
 *   GET  /api/public/health        — публичный (permitAll);
 *   GET  /api/tasks                — любой аутентифицированный;
 *   POST /api/tasks                — аутентифицированный (+ CSRF выключен: stateless);
 *   GET  /api/admin/stats          — только ROLE_ADMIN;
 *   DELETE /api/tasks/{id}         — только владелец задачи ИЛИ ADMIN (@PreAuthorize + ownership-бин).
 *
 * ЗАДАНИЕ — напишите тесты (используя @WithMockUser и MockMvc):
 *   1) Класс: @WebMvcTest(controllers = {PublicController07.class, TaskController07.class, AdminController07.class})
 *             + @Import({SecurityConfig07.class, TaskOwnership07.class}); @Autowired MockMvc.
 *
 *   2) ПУБЛИЧНЫЙ:
 *        public_no_auth_200():  get("/api/public/health") без аутентификации → isOk().
 *
 *   3) ЗАЩИЩЁННЫЙ:
 *        tasks_anonymous_401(): get("/api/tasks") → isUnauthorized();
 *        tasks_user_200():      @WithMockUser → get("/api/tasks") → isOk().
 *
 *   4) РОЛЕВОЙ:
 *        admin_user_403():  @WithMockUser(roles="USER")  → get("/api/admin/stats") → isForbidden();
 *        admin_admin_200(): @WithMockUser(roles="ADMIN") → get("/api/admin/stats") → isOk().
 *
 *   5) OWNERSHIP (задача id=1 принадлежит "alice"):
 *        owner_can_delete_204():  @WithMockUser("alice") → delete("/api/tasks/1") → isNoContent();
 *        stranger_cannot_403():   @WithMockUser("bob")   → delete("/api/tasks/1") → isForbidden();
 *        admin_can_delete_204():  @WithMockUser(username="bob", roles="ADMIN") → delete("/api/tasks/1") → isNoContent().
 *
 * ЦЕЛЬ-ПРОВЕРКА: матрица доступа полностью покрыта тестами — регрессия в правилах будет поймана.
 *
 * ВАЖНО: @EnableMethodSecurity нужен для @PreAuthorize (модуль 97); ownership-проверка вынесена
 *        в бин TaskOwnership07 и вызывается из SpEL (@taskOwnership07.isOwner(#id, authentication.name)).
 *
 * ПОДСКАЗКА: соедините приёмы задач 01–06 этого модуля + method security из модуля 97.
 */

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

class PublicController07 {
    @GetMapping("/health")
    String health() { return "UP"; }
}
