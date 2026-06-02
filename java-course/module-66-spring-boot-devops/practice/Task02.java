/**
 * Задача 02 — Модуль 66: Actuator — /health и /info
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-actuator:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ (в основном — конфигурация, кода почти нет):
 *   1) В application.properties откройте эндпоинты:
 *        management.endpoints.web.exposure.include=health,info
 *        management.endpoint.health.show-details=always
 *   2) Наполните /info:
 *        info.app.name=Shop Service
 *        info.app.version=1.0.0
 *   3) Запустите приложение и проверьте в браузере:
 *        http://localhost:8080/actuator/health   → {"status":"UP", ...}
 *        http://localhost:8080/actuator/info      → {"app":{"name":"Shop Service",...}}
 *
 * ВОПРОС: какой эндпоинт Actuator открыт по умолчанию, ещё ДО настройки exposure?
 *   (ответьте комментарием — подсказка в theory.md)
 *
 * ПРИМЕЧАНИЕ: этот класс — только точка входа. Вся «работа» — в application.properties.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
        // TODO: создайте application.properties с настройками Actuator (см. JavaDoc)
        // TODO: проверьте /actuator/health и /actuator/info в браузере
    }
}
