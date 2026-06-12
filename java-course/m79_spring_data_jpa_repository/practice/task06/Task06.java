package m79_spring_data_jpa_repository.practice.task06;

/**
 * Задача 06 — Модуль 79: Пагинация через Pageable / Page
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Добавьте derived-метод с пагинацией:
 *        Page<Product06> findByCategory(String category, Pageable pageable);
 *   2) В CommandLineRunner:
 *        - засейте 7 товаров категории "Электроника";
 *        - запросите 1-ю страницу по 3: PageRequest.of(0, 3, Sort.by("price").descending());
 *        - выведите: page.getContent().size(), page.getTotalElements(),
 *          page.getTotalPages(), page.hasNext().
 *        - используйте также готовый repo.findAll(PageRequest.of(0, 2)).
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем Page отличается от Slice? Когда Slice предпочтительнее?
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.domain.*;
 *   Page делает дополнительный COUNT-запрос (для totalElements/totalPages).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.IntStream;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
