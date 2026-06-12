package m79_spring_data_jpa_repository.practice.task04;

/**
 * Задача 04 — Модуль 79: Производные count / exists / delete
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository04 derived-методы агрегации и удаления:
 *     - long countByCategory(String category)
 *     - boolean existsBySku(String sku)
 *     - long deleteByCategory(String category)   // возвращает число удалённых
 *   В сервисе DeleteService04 (@Service) метод purge(category) пометьте @Transactional
 *   и вызовите deleteByCategory (модифицирующий запрос требует транзакции!).
 *   В CommandLineRunner: засейте данные, выведите count/exists, затем purge и снова count.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему deleteBy... нужно вызывать внутри @Transactional?
 *
 * ПОДСКАЗКА: модифицирующие запросы выполняются только в активной транзакции.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
