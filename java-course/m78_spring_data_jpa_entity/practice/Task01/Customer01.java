package m78_spring_data_jpa_entity.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: @Entity @Table(name = "customers")
class Customer01 {

    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected Customer01() {}
    public Customer01(String name) { this.name = name; }
}
