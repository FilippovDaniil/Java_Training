package m81_spring_data_jpa_transactions.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class OrderService05 {
    private final OrderRepository05 orders;
    private final AuditService05 audit;
    OrderService05(OrderRepository05 orders, AuditService05 audit) { this.orders = orders; this.audit = audit; }

    // TODO: @Transactional
    public void placeOrder(String name, boolean fail) {
        // TODO: audit.log("Попытка заказа: " + name);
        // TODO: orders.save(new Order05(name));
        // TODO: if (fail) throw new RuntimeException("сбой основной транзакции");
    }
}
