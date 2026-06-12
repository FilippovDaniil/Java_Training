package m66_spring_boot_devops.practice.task05;

/**
 * Задача 05 — Модуль 66: Интеграционный тест @SpringBootTest
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Запускать в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   1) DiscountService05 (@Service) уже готов: applyDiscount(price, percent)
 *      возвращает цену со скидкой. ИЗУЧИТЕ его.
 *   2) Класс Task05Test пометьте @SpringBootTest — поднимется весь контекст.
 *   3) Внедрите DiscountService05 через @Autowired (реальный бин из контекста).
 *   4) Напишите тесты:
 *        - applyDiscount(1000, 20) == 800
 *        - applyDiscount(1000, 0)  == 1000
 *        - applyDiscount(1000, 100) == 0
 *
 * ЦЕЛЬ: почувствовать разницу с unit-тестом — здесь работает НАСТОЯЩИЙ
 *       Spring-контекст и реальный бин (а не созданный через new).
 *
 * ПОДСКАЗКА:
 *   import static org.junit.jupiter.api.Assertions.assertEquals;
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: @SpringBootTest
class Task05Test {

    // TODO: @Autowired DiscountService05 discountService;

    @Test
    void discount20() {
        // TODO: assertEquals(800, discountService.applyDiscount(1000, 20))
    }

    @Test
    void discountZero() {
        // TODO: assertEquals(1000, discountService.applyDiscount(1000, 0))
    }

    @Test
    void discountFull() {
        // TODO: assertEquals(0, discountService.applyDiscount(1000, 100))
    }
}
