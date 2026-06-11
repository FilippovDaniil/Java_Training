package m77_spring_data_jpa_intro.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Сущности (каркасы)
// ============================================================

// TODO: @Entity @Table(name = "categories")
class Category07 {
    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Category07() {}
    public Category07(String name) { this.name = name; }
}
