package m78_spring_data_jpa_entity.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

enum ProductStatus07 { DRAFT, ACTIVE, ARCHIVED }
