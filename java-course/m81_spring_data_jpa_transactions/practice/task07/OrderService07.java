package m81_spring_data_jpa_transactions.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// TODO: @Service
@Service
class OrderService07 {

    private final ProductRepository07 products;
    private final OrderRepository07 orders;
    private final AuditService07 audit;

    @Autowired
    OrderService07(ProductRepository07 p, OrderRepository07 o, AuditService07 a) {
        this.products = p;
        this.orders = o;
        this.audit = a;
    }

    // TODO: @Transactional
    @Transactional
    public Order07 placeOrder(Long productId, int qty) {
        // TODO: audit.log("Заказ товара " + productId + " x" + qty);
        // TODO: Product07 p = products.findById(productId).orElseThrow();
        // TODO: if (p.getStock() < qty) throw new IllegalStateException("Недостаточно на складе");
        // TODO: p.setStock(p.getStock() - qty);              // dirty checking
        // TODO: return orders.save(new Order07(productId, qty));
        audit.log("Заказ товара " + productId + " x" + qty);
        Product07 p = products.findById(productId).orElseThrow();
        if (p.getStock() < qty) {
            throw new IllegalStateException("Недостаточно на складе");
        }
        p.setStock(p.getStock() - qty);
        return orders.save(new Order07(productId, qty));
    }
}
