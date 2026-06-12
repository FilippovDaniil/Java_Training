package m66_spring_boot_devops.practice.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
class PaymentRunner01 implements CommandLineRunner {
    private final PaymentService01 service;
    PaymentRunner01(PaymentService01 service) { this.service = service; }

    @Override
    public void run(String... args) {
        service.pay("ORD-1", 5000);
        service.pay("ORD-2", -10);
    }
}
