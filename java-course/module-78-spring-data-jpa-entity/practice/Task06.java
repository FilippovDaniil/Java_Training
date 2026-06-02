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

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

// TODO: @Embeddable
class OrderItemId06 implements Serializable {
    private Long orderId;
    private Long productId;

    protected OrderItemId06() {}
    public OrderItemId06(Long orderId, Long productId) { this.orderId = orderId; this.productId = productId; }

    // TODO: реализуйте equals(...) по (orderId, productId)
    @Override
    public boolean equals(Object o) {
        // TODO
        return false;
    }

    // TODO: реализуйте hashCode() по (orderId, productId)
    @Override
    public int hashCode() {
        // TODO: return Objects.hash(orderId, productId);
        return 0;
    }
}

@Entity
@Table(name = "order_items")
class OrderItem06 {
    // TODO: @EmbeddedId
    private OrderItemId06 id;

    private int quantity;

    protected OrderItem06() {}
    public OrderItem06(OrderItemId06 id, int quantity) { this.id = id; this.quantity = quantity; }
}
