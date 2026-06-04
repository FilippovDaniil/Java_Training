/**
 * Задача 06 — Модуль 78: Составной ключ через @EmbeddedId
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Позиция заказа уникальна по паре (orderId, productId) — составной ключ.
 *   1) Класс OrderItemId06 пометьте @Embeddable и реализуйте Serializable.
 *      Поля: Long orderId, Long productId.
 *      ОБЯЗАТЕЛЬНО реализуйте equals(...) и hashCode() по обоим полям
 *      (без них составной ключ работать НЕ будет).
 *   2) В OrderItem06 поле id (типа OrderItemId06) пометьте @EmbeddedId.
 *   3) Запустите: первичный ключ таблицы order_items — пара колонок (order_id, product_id).
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему для класса составного ключа обязательны equals и hashCode?
 *
 * ПОДСКАЗКА: Hibernate сравнивает ключи через equals/hashCode (как в HashMap).
 */

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.Serializable;
import java.util.Objects;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
