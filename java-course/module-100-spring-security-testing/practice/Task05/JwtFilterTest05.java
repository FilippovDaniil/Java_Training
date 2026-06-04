/**
 * Задача 05 — Модуль 100: тестирование собственного JWT-фильтра (реальный токен в заголовке)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test, io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Приложение использует СВОЙ JwtAuthFilter (модуль 99). Тестируем его реальным токеном.
 *     1) @WebMvcTest(TaskController05.class) + @Import({SecurityConfig05.class, JwtService05.class, JwtAuthFilter05.class}).
 *          ⚠️ НЕ мокать сам JwtAuthFilter05 (doFilter в OncePerRequestFilter — final; @MockBean даст
 *             200 с пустым телом). Поднимаем реальный фильтр + его реальную зависимость JwtService05.
 *     2) @Autowired MockMvc; @Autowired JwtService05 jwt;
 *     3) valid_token_200(): token = jwt.generateToken("alice", List.of("ROLE_USER"));
 *          get("/api/tasks").header("Authorization","Bearer "+token) → isOk().
 *     4) no_token_401(): get без заголовка → isUnauthorized().
 *     5) garbage_token_401(): header("Authorization","Bearer not-a-jwt") → isUnauthorized() (а НЕ 500).
 *
 * ЦЕЛЬ: проверить полный путь токена через свой фильтр; убедиться, что битый токен → 401, не 500.
 *
 * ВАЖНО (из практики): для своего фильтра .with(jwt()) НЕ подходит — он для oauth2ResourceServer.
 *        Используем настоящий подписанный токен в заголовке.
 *
 * ПОДСКАЗКА: JwtService05 и JwtAuthFilter05 ниже — упрощённые версии из модуля 98/99.
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: @WebMvcTest(TaskController05.class)
// TODO: @Import({SecurityConfig05.class, JwtService05.class, JwtAuthFilter05.class})
class JwtFilterTest05 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired JwtService05 jwt;

    @Test
    void valid_token_200() throws Exception {
        // TODO: String token = jwt.generateToken("alice", List.of("ROLE_USER"));
        // TODO: mockMvc.perform(get("/api/tasks").header("Authorization", "Bearer " + token))
        //              .andExpect(status().isOk());
    }

    @Test
    void no_token_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
    }

    @Test
    void garbage_token_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks").header("Authorization", "Bearer not-a-jwt"))
        //              .andExpect(status().isUnauthorized());
    }
}
