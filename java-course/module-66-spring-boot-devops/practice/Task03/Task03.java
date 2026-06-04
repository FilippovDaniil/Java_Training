/**
 * Задача 03 — Модуль 66: Кастомный HealthIndicator
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-actuator:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) WarehouseHealthIndicator03 пометьте @Component и реализуйте HealthIndicator.
 *   2) Метод health():
 *        - если itemsInStock() > 0 → Health.up().withDetail("items", <кол-во>).build()
 *        - иначе                   → Health.down().withDetail("reason", "склад пуст").build()
 *   3) Откройте health с деталями в application.properties:
 *        management.endpoints.web.exposure.include=health
 *        management.endpoint.health.show-details=always
 *   4) Запустите и проверьте http://localhost:8080/actuator/health —
 *      ваш индикатор "warehouse" должен появиться в составе общего статуса.
 *
 * ЦЕЛЬ: показать, как приложение сообщает наружу о здоровье своих подсистем.
 *
 * ПОДСКАЗКА:
 *   Имя индикатора в JSON берётся из имени класса без суффикса HealthIndicator
 *   ("WarehouseHealthIndicator03" → "warehouse03"). Это нормально.
 */

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
