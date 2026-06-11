package m77_spring_data_jpa_intro.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// TODO: @Entity @Table(name = "categories")
class Category05 {
    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected Category05() {}
    public Category05(String name) { this.name = name; }
    public String getName() { return name; }
}
