/**
 * Задача 05 — Модуль 28: @DisplayName, assertAll, @Disabled
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс Person реализован. Напишите тесты:
 *     1) тест с @DisplayName("Создание человека") — через assertAll
 *        проверьте сразу несколько полей (имя, возраст, совершеннолетие);
 *     2) тест с @DisplayName, проверяющий isAdult() для взрослого и ребёнка;
 *     3) один тест пометьте @Disabled с указанием причины — убедитесь,
 *        что он пропускается при запуске.
 *
 * ПОДСКАЗКА:
 *   assertAll("person",
 *       () -> assertEquals("Иван", p.getName()),
 *       () -> assertEquals(30, p.getAge()),
 *       () -> assertTrue(p.isAdult()));
 */
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task05 {

    // TODO: тесты с @DisplayName и assertAll; один тест с @Disabled

}

// Класс под тестом (готов)
class Person {
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() { return name; }
    int getAge() { return age; }
    boolean isAdult() { return age >= 18; }
}
