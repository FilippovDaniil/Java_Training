/**
 * Задача 06 — Модуль 14: Множество объектов (equals/hashCode)
 *
 * ЗАДАНИЕ:
 *   Класс City (name, country). Переопределите equals() и hashCode()
 *   так, чтобы города считались одинаковыми при совпадении name и country.
 *   Добавьте в HashSet<City> несколько городов, включая дубликат,
 *   и убедитесь, что дубликат не сохранился (выведите размер).
 *
 * ПРИМЕР:
 *   Добавили: Москва/Россия, Париж/Франция, Москва/Россия
 *   Уникальных городов: 2
 *
 * ВНИМАНИЕ:
 *   Без equals/hashCode HashSet будет считать «одинаковые» города
 *   разными объектами и сохранит оба.
 *
 * ПОДСКАЗКА:
 *   Objects.hash(name, country) для hashCode.
 */
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task06 {
    public static void main(String[] args) {
        // Создайте HashSet<City>, добавьте города с дубликатом, выведите размер
    }
}

class City {
    String name;
    String country;

    City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // TODO: переопределите equals() и hashCode()
}
