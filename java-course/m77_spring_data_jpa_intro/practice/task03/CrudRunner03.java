package m77_spring_data_jpa_intro.practice.task03;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
class CrudRunner03 implements CommandLineRunner {

    private final ProductRepository03 repo;

    @Autowired
    CrudRunner03(ProductRepository03 repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // ============ CREATE ============
        Product03 saved = repo.save(new Product03("Кофе", 500));
        Long id = saved.getId();
        System.out.println("Создан: " + saved.getName() + " (id=" + id + ")");

        // ============ READ ============
        repo.findById(id).ifPresent(p ->
                System.out.println("Найден: " + p.getName() + ", цена: " + p.getPrice())
        );

        // ============ UPDATE ============
        // Вариант 1: Использовать ifPresent
        repo.findById(id).ifPresent(p -> {
            p.setPrice(550);
            repo.save(p);
            System.out.println("Обновлён: " + p.getName() + ", цена: " + p.getPrice());
        });

        // Вариант 2: Использовать orElseThrow
        Product03 find = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        find.setPrice(550);
        repo.save(find);
        System.out.println("Обновлён (через orElseThrow): " + find.getName());

        // ============ DELETE ============
        repo.deleteById(id);
        System.out.println("Существует после удаления: " + repo.existsById(id));
    }
}
