package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

interface ProductRepository07 extends JpaRepository<Product07, Long> {
    // TODO: List<Product07> findByCategory(String category);
    // TODO: List<Product07> findByCategoryAndAvailableTrue(String category);
    // TODO: List<Product07> findByPriceBetween(long min, long max);
    // TODO: List<Product07> findByNameContainingIgnoreCase(String part);
    // TODO: long countByCategory(String category);
    // TODO: Page<Product07> findByAvailableTrue(Pageable pageable);
    // TODO: List<Product07> findTop5ByOrderByPriceDesc();
}
