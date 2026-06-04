import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;

enum OrderStatus03 { NEW, PAID, SHIPPED, DELIVERED, CANCELLED }
