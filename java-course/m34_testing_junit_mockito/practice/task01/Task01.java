package m34_testing_junit_mockito.practice.task01;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Task01 {

    @Mock
    UserRepository repo = mock(UserRepository.class);

    @InjectMocks
    UserService userService = new UserService(repo);

    @Test
    void testGreet() {
        // Создайте мок, настройте when/thenReturn, проверьте результат
        when(repo.findName(1)).thenReturn("Ivan");
        assertEquals("Привет, Ivan",userService.greet(1));
    }
}
