package m66_spring_boot_devops.practice.task03;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// TODO: @Component
class WarehouseHealthIndicator03 implements HealthIndicator {

    private int itemsInStock() {
        return 1500; // эмуляция запроса к складу; поменяйте на 0 и сравните статус
    }

    @Override
    public Health health() {
        // TODO: если itemsInStock() > 0 — Health.up().withDetail("items", ...).build()
        // TODO: иначе — Health.down().withDetail("reason", "склад пуст").build()
        return null;
    }
}
