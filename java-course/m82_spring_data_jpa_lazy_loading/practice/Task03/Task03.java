package m82_spring_data_jpa_lazy_loading.practice.task03;

/**
 * Задача 03 — Модуль 82: увидеть проблему N+1 «вживую»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   В application.properties включите: spring.jpa.show-sql=true
 *
 * ЗАДАНИЕ:
 *   1) reportNaive(): @Transactional. Загрузить ВСЕ категории (findAll), затем в цикле
 *      по каждой вывести "имя: <products.size()> товаров".
 *   2) Запустите и посчитайте в логе количество SELECT'ов: должно быть 1 (категории)
 *      + по одному на products каждой категории = 1 + N запросов. Это и есть N+1.
 *
 * ЦЕЛЬ: научиться диагностировать N+1 по логу SQL — до того, как «лечить».
 *
 * ПОДСКАЗКА: метод транзакционный, чтобы ленивые products вообще загрузились
 *            (иначе был бы LazyInitializationException, см. Task02). Здесь цель —
 *            именно посчитать запросы, а не упасть.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
