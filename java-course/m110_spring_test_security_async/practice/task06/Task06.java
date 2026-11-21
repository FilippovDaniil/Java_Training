package m110_spring_test_security_async.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) { SpringApplication.run(m110_spring_test_security_async.practice.task06.Task06.class, args); }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(a -> a.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(c -> c.disable());   // ← отключить CSRF для API
        return http.build();
    }
}