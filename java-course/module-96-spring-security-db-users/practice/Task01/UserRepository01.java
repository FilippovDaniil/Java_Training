import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

interface UserRepository01 extends JpaRepository<UserEntity01, Long> {
    // TODO: Optional<UserEntity01> findByUsername(String username);
    // TODO: boolean existsByUsername(String username);
}
