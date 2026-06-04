import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// TODO: объявите интерфейс ProductRepository02 extends JpaRepository<Product02, Long>

@Component
class SeedRunner02 implements CommandLineRunner {

    // TODO: внедрите ProductRepository02 через конструктор

    @Override
    public void run(String... args) {
        // TODO: repo.save(new Product02("Кофе", 500)); repo.save(new Product02("Чай", 300));
        // TODO: System.out.println("Всего товаров: " + repo.count());
        // TODO: repo.findAll().forEach(p -> System.out.println(p.getName()));
    }
}
