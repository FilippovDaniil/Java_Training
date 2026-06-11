package m82_spring_data_jpa_lazy_loading.practice.task07;

/**
 * Задача 07 — Модуль 82: МИНИ-ПРОЕКТ «Эффективная витрина каталога shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   В application.properties: spring.jpa.show-sql=true, spring.jpa.open-in-view=false
 *
 * ЦЕЛЬ: собрать один сервис каталога, который три типовых экрана отдаёт БЕЗ N+1 и
 *       без LazyInitializationException, осознанно выбирая приём под каждый кейс.
 *
 * МОДЕЛЬ:
 *   Category07 (1) —— (N) Product07.  Все связи LAZY.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Репозиторий CategoryRepository07:
 *     1) @Query("select distinct c from Category07 c join fetch c.products")
 *        List<Category07> findAllWithProducts();              // экран «категория + её товары»
 *     2) @EntityGraph(attributePaths = "products")
 *        List<Category07> findByNameContaining(String part);  // поиск по имени с товарами
 *     3) @Query("select new CatalogRow07(c.name, count(p)) from Category07 c " +
 *               "left join c.products p group by c.id, c.name")
 *        List<CatalogRow07> overview();                       // сводка для главной (DTO)
 *
 *   Сервис CatalogService07 (@Service):
 *     - listWithProducts(): @Transactional — печатает каждую категорию и её products.size();
 *     - search(part): @Transactional — печатает найденные категории и число товаров;
 *     - overview(): БЕЗ @Transactional — печатает сводку (имя -> количество).
 *
 *   Runner07: засейте 3 категории (по 2 товара) + 1 пустую; вызовите все три метода;
 *     в логе убедитесь, что каждый экран — это 1 (или 1+агрегат) запрос, а НЕ 1+N.
 *
 * АРХИТЕКТУРА (приём под кейс):
 *
 *   «категория + товары»  → JOIN FETCH (полный контроль, distinct от дублей)
 *   «поиск + товары»      → @EntityGraph (декларативно поверх derived query)
 *   «сводка для главной»  → DTO-проекция (сущности не нужны → нет lazy/N+1 вовсе)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - listWithProducts(): один SELECT с JOIN, выведены 3 категории по 2 товара;
 *   - search("ни"): найдена "Книги" (1 товарная пара) одним JOIN-запросом;
 *   - overview(): 4 строки (3 по 2 + пустая с 0) одним агрегирующим запросом.
 *
 * ПОДСКАЗКА: соберите вместе Task04 (JOIN FETCH), Task05 (@EntityGraph), Task06 (DTO).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
