/**
 * Задача 03 — Модуль 78: Перечисление (@Enumerated) и java.time
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Поле status (enum OrderStatus03) пометьте @Enumerated(EnumType.STRING) —
 *      чтобы в БД хранилось "NEW"/"PAID"/..., а не порядковый номер.
 *   2) Поля createdAt (LocalDateTime) и dueDate (LocalDate) оставьте как есть —
 *      java.time маппится автоматически (TIMESTAMP/DATE), @Temporal НЕ нужен.
 *   3) Запустите, посмотрите тип колонки status в DDL (должен быть VARCHAR).
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему EnumType.ORDINAL опасен? Что сломается при перестановке констант?
 *
 * ПОДСКАЗКА: ORDINAL хранит индекс (0,1,2...) — вставка новой константы в середину сместит смысл.
 */
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

enum OrderStatus03 { NEW, PAID, SHIPPED, DELIVERED, CANCELLED }

@Entity
@Table(name = "orders")
class Order03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Enumerated(EnumType.STRING)
    private OrderStatus03 status;

    private LocalDateTime createdAt;   // авто-маппинг → TIMESTAMP
    private LocalDate dueDate;         // авто-маппинг → DATE

    protected Order03() {}
    public Order03(OrderStatus03 status, LocalDate dueDate) {
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
    }
}
