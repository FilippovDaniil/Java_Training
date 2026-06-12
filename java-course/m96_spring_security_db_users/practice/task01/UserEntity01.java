package m96_spring_security_db_users.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Entity @Table(name = "users")
class UserEntity01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: @Column(unique = true, nullable = false)
    private String username;
    // TODO: @Column(nullable = false)
    private String password;
    private boolean enabled = true;
    protected UserEntity01() {}
    public UserEntity01(String username, String password) { this.username = username; this.password = password; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isEnabled() { return enabled; }
}
