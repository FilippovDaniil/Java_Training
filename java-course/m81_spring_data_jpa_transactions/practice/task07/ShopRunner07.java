package m81_spring_data_jpa_transactions.practice.task07;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// ============================================================
// Runner (каркас)
// ============================================================

// TODO: @Component
@Slf4j
@Component
class ShopRunner07 implements CommandLineRunner {

    private final ProductRepository07 products;
    private final OrderRepository07 orders;
    private final AuditRepository07 audits;
    private final OrderService07 service;

    @Autowired
    ShopRunner07(ProductRepository07 p, OrderRepository07 o, AuditRepository07 a, OrderService07 s) {
        this.products = p;
        this.orders = o;
        this.audits = a;
        this.service = s;
    }

    @Override
    public void run(String... args) {
        Product07 laptop = products.save(new Product07("Ноутбук", 2));
        // TODO: успешный заказ: service.placeOrder(laptop.getId(), 1);
        // TODO: неуспешный: try { service.placeOrder(laptop.getId(), 5); } catch (Exception e) { ... }
        // TODO: выведите остаток (products.findById...getStock()), orders.count(), audits.count()
        //       Ожидается: остаток=1, заказов=1, аудитов=2
        service.placeOrder(laptop.getId(), 1);
        try {
            service.placeOrder(laptop.getId(), 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        log.info("Остаток = {}", products.findById(2l).get());
        log.info("Заказов = {}", orders.count());
        log.info("Аудитов = {}", audits.count());
    }
}
