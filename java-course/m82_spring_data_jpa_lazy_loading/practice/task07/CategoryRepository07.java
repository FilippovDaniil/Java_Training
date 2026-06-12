package m82_spring_data_jpa_lazy_loading.practice.task07;

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

// ============================================================
// Репозиторий (каркас)
// ============================================================
interface CategoryRepository07 extends JpaRepository<Category07, Long> {
    // TODO: @Query("select distinct c from Category07 c join fetch c.products")
    // TODO: List<Category07> findAllWithProducts();

    // TODO: @EntityGraph(attributePaths = "products")
    // TODO: List<Category07> findByNameContaining(String part);

    // TODO: @Query("select new CatalogRow07(c.name, count(p)) from Category07 c " +
    // TODO:        "left join c.products p group by c.id, c.name")
    // TODO: List<CatalogRow07> overview();
}
