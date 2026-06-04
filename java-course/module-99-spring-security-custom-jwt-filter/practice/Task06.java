/**
 * Задача 06 — Модуль 99: встроенный resource server (oauth2ResourceServer.jwt) — без своего фильтра
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   spring-boot-starter-oauth2-resource-server (NimbusJwtDecoder, oauth2ResourceServer DSL).
 *
 * ЗАДАНИЕ:
 *   Решите ту же задачу проверки Bearer-JWT БЕЗ собственного OncePerRequestFilter —
 *   средствами Spring Security «из коробки».
 *     1) Бин JwtDecoder:
 *          var key = new SecretKeySpec(secret.getBytes(UTF_8), "HmacSHA256");
 *          return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
 *     2) Бин SecurityFilterChain:
 *          - "/api/auth/**" permitAll, anyRequest authenticated;
 *          - sessionCreationPolicy(STATELESS); csrf.disable();
 *          - oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()));
 *
 * ЦЕЛЬ: увидеть альтернативу custom-фильтру и понять компромисс
 *       (меньше кода ↔ конвенции Nimbus вместо jjwt).
 *
 * ВАЖНО: oauth2ResourceServer использует Nimbus (НЕ jjwt). Роли по умолчанию берутся из claim
 *        scope/scp; чтобы маппить свой claim "roles" в authorities — нужен JwtAuthenticationConverter.
 *
 * ПОДСКАЗКА: секрет/алгоритм должны совпадать с тем, чем подписывали токен при выдаче (HS256).
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class Task06 {

    @Bean
    JwtDecoder jwtDecoder(@Value("${jwt.secret:01234567890123456789012345678901}") String secret) {
        // TODO: var key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        // TODO: return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:         .requestMatchers("/api/auth/**").permitAll()
        // TODO:         .anyRequest().authenticated())
        // TODO:     .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // TODO:     .csrf(csrf -> csrf.disable())
        // TODO:     .oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()));   // Spring сам читает Bearer
        return http.build();
    }
}
