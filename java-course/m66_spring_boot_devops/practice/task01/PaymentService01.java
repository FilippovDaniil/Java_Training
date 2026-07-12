package m66_spring_boot_devops.practice.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
class PaymentService01 {
    // TODO: объявите логгер через LoggerFactory.getLogger(...)
    private static final Logger log = LoggerFactory.getLogger(PaymentService01.class);

    public void pay(String orderId, long amount) {
        // TODO: log.info(...), при amount<=0 log.warn(...), log.debug(...)
        log.info("Обрабатываем заказ {}", orderId);   // {} — плейсхолдер, без конкатенации
        log.debug("Детали заказа {}", orderId);
        if (amount <= 0) log.warn("id == null!");
    }
}
