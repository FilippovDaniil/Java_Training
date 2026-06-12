package m82_spring_data_jpa_lazy_loading.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

interface CategoryRepository05 extends JpaRepository<Category05, Long> {
    // TODO: @EntityGraph(attributePaths = "products")
    // TODO: List<Category05> findByNameContaining(String part);
}
