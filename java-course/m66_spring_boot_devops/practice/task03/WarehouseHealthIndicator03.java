package m66_spring_boot_devops.practice.task03;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component  // ← ДОБАВЛЯЕМ
class WarehouseHealthIndicator03 implements HealthIndicator {

    private int itemsInStock() {
        return 1500; // эмуляция запроса к складу; поменяйте на 0 и сравните статус
    }

    @Override
    public Health health() {
        int items = itemsInStock();

        if (items > 0) {
            // Склад не пуст - здоров
            return Health.up()
                    .withDetail("items", items)
                    .build();
        } else {
            // Склад пуст - не здоров
            return Health.down()
                    .withDetail("reason", "склад пуст")
                    .build();
        }
    }
}
