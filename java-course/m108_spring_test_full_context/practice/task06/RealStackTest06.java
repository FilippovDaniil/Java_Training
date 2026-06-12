package m108_spring_test_full_context.practice.task06;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task06.class)
// TODO: @Transactional
class RealStackTest06 {

    // TODO: @Autowired TaskService06 service;
    // TODO: @Autowired TaskRepository06 repository;

    @Test
    void create_persists_through_real_stack() {
        // TODO: Long id = service.create("Кофе");
        // TODO: assertThat(repository.findById(id)).isPresent();
        // TODO: assertThat(repository.findById(id).orElseThrow().getTitle()).isEqualTo("Кофе");
    }
}
