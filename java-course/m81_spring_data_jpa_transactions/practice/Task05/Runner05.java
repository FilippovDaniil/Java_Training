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

@Component
class Runner05 implements CommandLineRunner {
    private final OrderRepository05 orders;
    private final AuditRepository05 audits;
    private final OrderService05 service;
    Runner05(OrderRepository05 o, AuditRepository05 a, OrderService05 s) { this.orders = o; this.audits = a; this.service = s; }

    @Override
    public void run(String... args) {
        try { service.placeOrder("Заказ-1", true); } catch (Exception e) { System.out.println("Откат: " + e.getMessage()); }
        // TODO: System.out.println("Заказов: " + orders.count() + " (ожидается 0)");
        // TODO: System.out.println("Аудитов: " + audits.count() + " (ожидается 1)");
    }
}
