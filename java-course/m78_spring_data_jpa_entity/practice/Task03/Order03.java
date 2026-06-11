package m78_spring_data_jpa_entity.practice.task03;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
