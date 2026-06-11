package m82_spring_data_jpa_lazy_loading.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

interface CategoryRepository06 extends JpaRepository<Category06, Long> {
    // TODO: @Query("select new CategorySummary06(c.name, count(p)) " +
    // TODO:        "from Category06 c left join c.products p group by c.id, c.name")
    // TODO: List<CategorySummary06> summaries();
}
