package m28_junit.practice.task05;

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

    // Тест 1: Создание человека - проверка нескольких полей через assertAll
    @Test
    @DisplayName("Создание человека")
    void testPersonCreation() {
        Person person = new Person("Иван", 25);

        assertAll("person",
                () -> assertEquals("Иван", person.getName(), "Name should be 'Иван'"),
                () -> assertEquals(25, person.getAge(), "Age should be 25"),
                () -> assertTrue(person.isAdult(), "Person should be adult at age 25")
        );
    }

    // Тест 2: Проверка isAdult() для взрослого и ребёнка
    @Test
    @DisplayName("Проверка совершеннолетия для разных возрастов")
    void testIsAdultForDifferentAges() {
        // Тестирование взрослого человека
        Person adult = new Person("Пётр", 30);
        assertAll("adult person",
                () -> assertEquals("Пётр", adult.getName()),
                () -> assertEquals(30, adult.getAge()),
                () -> assertTrue(adult.isAdult(), "Person aged 30 should be adult")
        );

        // Тестирование ребёнка
        Person child = new Person("Мария", 15);
        assertAll("child person",
                () -> assertEquals("Мария", child.getName()),
                () -> assertEquals(15, child.getAge()),
                () -> assertFalse(child.isAdult(), "Person aged 15 should not be adult")
        );

        // Тестирование граничного случая - ровно 18 лет
        Person exactlyAdult = new Person("Алексей", 18);
        assertTrue(exactlyAdult.isAdult(), "Person aged exactly 18 should be adult");

        // Тестирование граничного случая - 17 лет
        Person notAdult = new Person("Анна", 17);
        assertFalse(notAdult.isAdult(), "Person aged 17 should not be adult");
    }

    // Тест 3: Отключенный тест с указанием причины
    @Test
    @Disabled("Этот тест временно отключен, так как требуется доработка логики isAdult() для граничных случаев")
    @DisplayName("Проверка совершеннолетия для пограничных значений (отключен)")
    void testIsAdultForBoundaryValues() {
        // Этот тест пропускается при запуске из-за @Disabled
        Person person = new Person("Елена", 17);
        assertFalse(person.isAdult(), "Person aged 17 should not be adult");

        Person person2 = new Person("Дмитрий", 18);
        assertTrue(person2.isAdult(), "Person aged 18 should be adult");

        Person person3 = new Person("Ольга", 0);
        assertFalse(person3.isAdult(), "Newborn should not be adult");
    }

    // Дополнительный тест: Проверка нескольких разных персон
    @Test
    @DisplayName("Создание и проверка нескольких персон с разными характеристиками")
    void testMultiplePersons() {
        Person[] persons = {
                new Person("Александр", 35),
                new Person("Екатерина", 22),
                new Person("Сергей", 16),
                new Person("Татьяна", 8)
        };

        // Проверка взрослых
        assertAll("adults",
                () -> assertTrue(persons[0].isAdult(), "35 years old should be adult"),
                () -> assertTrue(persons[1].isAdult(), "22 years old should be adult")
        );

        // Проверка детей
        assertAll("children",
                () -> assertFalse(persons[2].isAdult(), "16 years old should not be adult"),
                () -> assertFalse(persons[3].isAdult(), "8 years old should not be adult")
        );

        // Проверка имен
        assertAll("names",
                () -> assertEquals("Александр", persons[0].getName()),
                () -> assertEquals("Екатерина", persons[1].getName()),
                () -> assertEquals("Сергей", persons[2].getName()),
                () -> assertEquals("Татьяна", persons[3].getName())
        );
    }
}