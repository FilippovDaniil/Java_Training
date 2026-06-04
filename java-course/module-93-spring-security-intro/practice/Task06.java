/**
 * Задача 06 — Модуль 93: свой фильтр в цепочке (знакомство с filter chain)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Чтобы прочувствовать «цепочку фильтров», добавьте СВОЙ фильтр, который логирует
 *   каждый запрос ДО фильтров аутентификации.
 *   1) RequestLogFilter06 extends OncePerRequestFilter; в doFilterInternal:
 *        System.out.println("[FILTER] " + request.getMethod() + " " + request.getRequestURI());
 *        chain.doFilter(request, response);   // обязательно передать дальше по цепочке!
 *   2) В SecurityFilterChain вставьте его раньше штатного фильтра аутентификации:
 *        http.addFilterBefore(new RequestLogFilter06(),
 *            org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
 *   3) Правила: /public/** permitAll, остальное authenticated; httpBasic; csrf.disable.
 *   4) Проверьте: в лог пишется КАЖДЫЙ запрос (и публичный, и защищённый).
 *
 * ЦЕЛЬ: понять, что Security — это цепочка фильтров, в которую можно встроить свой
 *       (фундамент для JWT-фильтра в модуле 99).
 *
 * ПОДСКАЗКА: забыть chain.doFilter() = оборвать цепочку (запрос «зависнет»/не дойдёт дальше).
 */
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

class RequestLogFilter06 extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // TODO: System.out.println("[FILTER] " + request.getMethod() + " " + request.getRequestURI());
        // TODO: chain.doFilter(request, response);   // ОБЯЗАТЕЛЬНО — передать дальше
        chain.doFilter(request, response);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig06 {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/public/**").permitAll()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.addFilterBefore(new RequestLogFilter06(), UsernamePasswordAuthenticationFilter.class);
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class PingController06 {
    @GetMapping("/public/ping")
    String ping() { return "pong"; }

    @GetMapping("/api/secure")
    String secure() { return "секрет"; }
}
