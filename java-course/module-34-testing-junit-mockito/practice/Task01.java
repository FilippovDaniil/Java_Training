/**
 * Задача 01 — Модуль 34: Первый мок (mock + when + thenReturn)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito (см. theory.md).
 * Это тест, а не программа с main — запускайте в IDE/Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс UserService зависит от UserRepository. Напишите тест:
 *     1) создайте мок UserRepository (mock(...));
 *     2) настройте: при findName(1) мок возвращает "Иван";
 *     3) создайте UserService с этим моком;
 *     4) проверьте, что service.greet(1) возвращает "Привет, Иван".
 *
 * ПОДСКАЗКА:
 *   UserRepository repo = mock(UserRepository.class);
 *   when(repo.findName(1)).thenReturn("Иван");
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Task01 {

    @Test
    void testGreet() {
        // Создайте мок, настройте when/thenReturn, проверьте результат
    }
}

// Классы под тестом (готовы)
interface UserRepository {
    String findName(long id);
}

class UserService {
    private final UserRepository repo;
    UserService(UserRepository repo) { this.repo = repo; }
    String greet(long id) { return "Привет, " + repo.findName(id); }
}
